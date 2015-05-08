/*--

 $Id: ContentList.java,v 1.3 2011/05/10 12:24:16 sbhattac Exp $

 Copyright (C) 2000 Jason Hunter & Brett McLaughlin.
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:

 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions, and the following disclaimer.

 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions, and the disclaimer that follows
    these conditions in the documentation and/or other materials
    provided with the distribution.

 3. The name "JDOM" must not be used to endorse or promote products
    derived from this software without prior written permission.  For
    written permission, please contact <request_AT_jdom_DOT_org>.

 4. Products derived from this software may not be called "JDOM", nor
    may "JDOM" appear in their name, without prior written permission
    from the JDOM Project Management <request_AT_jdom_DOT_org).

 In addition, we request (but do not require) that you include in the
 end-user documentation provided with the redistribution and/or in the
 software itself an acknowledgement equivalent to the following:
     "This product includes software developed by the
      JDOM Project (http://www.jdom.org/)."
 Alternatively, the acknowledgment may be graphical using the logos
 available at http://www.jdom.org/images/logos.

 THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 SUCH DAMAGE.

 This software consists of voluntary contributions made by many
 individuals on behalf of the JDOM Project and was originally
 created by Jason Hunter <jhunter_AT_jdom_DOT_org> and
 Brett McLaughlin <brett_AT_jdom_DOT_org>.  For more information
 on the JDOM Project, please see <http://www.jdom.org/>.

 */

package org.jdom;

import java.util.*;

import org.jdom.filter.*;

/**
 * <code>ContentList</code> represents legal JDOM content, including content
 * for <code>Document</code>s or <code>Element</code>s.
 * This class is NOT PUBLIC; users should see it as a simple List
 * implementation.
 *
 * @author Alex Rosen
 * @author Philippe Riand
 * @author Bradley S. Huffman
 * @version $Revision: 1.3 $, $Date: 2011/05/10 12:24:16 $
 * @see CDATA
 * @see Comment
 * @see Element
 * @see EntityRef
 * @see ProcessingInstruction
 * @see Text
 */
class ContentList extends AbstractList implements java.io.Serializable {

    private static final String CVS_ID =
      "@(#) $RCSfile: ContentList.java,v $ $Revision: 1.3 $ $Date: 2011/05/10 12:24:16 $ $Name: EZTO_HA_2011 $";

    private static final int INITIAL_ARRAY_SIZE = 5;

    /**
     * Used inner class FilterListIterator to help hasNext and
     * hasPrevious the next index of our cursor (must be here
     * for JDK1.1).
     */
    private static final int CREATE  = 0;
    private static final int HASPREV = 1;
    private static final int HASNEXT = 2;
    private static final int PREV    = 3;
    private static final int NEXT    = 4;
    private static final int ADD     = 5;
    private static final int REMOVE  = 6;

    /** Our backing list */
//    protected ArrayList list;
    private Object elementData[];
    private int size;

    /** Document or Element this list belongs to */
    protected Object parent;

    /** Force either a Document or Element parent */
    private ContentList() { }

    /**
     * Create a new instance of the ContentList representing
     * Document content
     */
    protected ContentList(Document document) {
        this.parent = document;
    }

    /**
     * Create a new instance of the ContentList representing
     * Element content
     */
    protected ContentList(Element parent) {
        this.parent = parent;
    }

    /**
     * Inserts the specified object at the specified position in this list.
     * Shifts the object currently at that position (if any) and any
     * subsequent objects to the right (adds one to their indices).
     *
     * @param index The location to set the value to.
     * @param obj The object to insert into the list.
     * throws IndexOutOfBoundsException if index < 0 || index > size()
     */
    public void add(int index, Object obj) {
        if (obj instanceof Element) {
            add(index, (Element) obj);
        }
        else if (obj instanceof Text) {
            add(index, (Text) obj);
        }
        else if (obj instanceof Comment) {
            add(index, (Comment) obj);
        }
        else if (obj instanceof ProcessingInstruction) {
            add(index, (ProcessingInstruction) obj);
        }
        else if (obj instanceof EntityRef) {
            add(index, (EntityRef) obj);
        }
        else {
            if (obj == null) {
                throw new IllegalAddException("Cannot add null object");
            }
            else {
                throw new IllegalAddException("Class " +
                             obj.getClass().getName() + 
                             " is of unrecognized type and cannot be added");
            }
        }
    }

    /**
     * Check and add the <code>Element</code> to this list at
     * the given index.
     *
     * @param index index where to add <code>Element</code>
     * @param element <code>Element</code> to add
     */
    protected void add(int index, Element element) {
        if (element == null) {
            throw new IllegalAddException("Cannot add null object");
        }

        if (element.getParent() != null) {
            throw new IllegalAddException(
                          "The element already has an existing parent \"" +
                          element.getParent().getQualifiedName() + "\"");
        }

        if (element.getDocument() != null) {
            throw new IllegalAddException(element.getDocument(), element,
                          "The element already has an existing parent");
        }

        if (element == parent) {
            throw new IllegalAddException(
                "The element cannot be added to itself");
        }

        if ((parent instanceof Element) &&
                ((Element) parent).isAncestor(element)) {
            throw new IllegalAddException(
                "The element cannot be added as a descendent of itself");
        }

        if (index<0 || index>size) {
            throw new IndexOutOfBoundsException("Index: " + index +
                                                " Size: " + size());
        }

        if (parent instanceof Document) {
            if (indexOfFirstElement() >= 0) {
                throw new IllegalAddException(
                  "Cannot add a second root element, only one is allowed");
            }
            element.setDocument((Document) parent);
        }
        else {
            element.setParent((Element) parent);
        }

        ensureCapacity(size+1);
        if( index==size ) {
            elementData[size++] = element;
        } else {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = element;
            size++;
        }
        modCount++;
    }

    /**
     * Check and add the <code>Comment</code> to this list at
     * the given index.
     *
     * @param index index where to add <code>Comment</code>
     * @param comment <code>Comment</code> to add
     */
    protected void add(int index, Comment comment) {
        if (comment == null) {
            throw new IllegalAddException("Cannot add null object");
        }

        if (comment.getParent() != null) {
            throw new IllegalAddException(
                          "The comment already has an existing parent \"" +
                          comment.getParent().getQualifiedName() + "\"");
        }

        if (comment.getDocument() != null) {
            throw new IllegalAddException(comment.getDocument(), comment,
                          "The comment already has an existing parent");
        }

        if (index<0 || index>size) {
            throw new IndexOutOfBoundsException("Index: " + index +
                                                " Size: " + size());
        }

        if (parent instanceof Document) {
            comment.setDocument((Document) parent);
        }
        else {
            comment.setParent((Element) parent);
        }

        ensureCapacity(size+1);
        if( index==size ) {
            elementData[size++] = comment;
        } else {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = comment;
            size++;
        }
        modCount++;
    }

    /**
     * Check and add the <code>ProcessingInstruction</code> to this list at
     * the given index.
     *
     * @param index index where to add <code>ProcessingInstruction</code>
     * @param pi <code>ProcessingInstruction</code> to add
     */
    protected void add(int index, ProcessingInstruction pi) {
        if (pi == null) {
            throw new IllegalAddException("Cannot add null object");
        }

        if (pi.getParent() != null) {
            throw new IllegalAddException(
                          "The PI already has an existing parent \"" +
                          pi.getParent().getQualifiedName() + "\"");
        }

        if (pi.getDocument() != null) {
            throw new IllegalAddException(pi.getDocument(), pi,
                          "The processing instruction already has an existing parent");
        }

        if (index<0 || index>size) {
            throw new IndexOutOfBoundsException("Index: " + index +
                                                " Size: " + size());
        }

        if (parent instanceof Document) {
            pi.setDocument((Document) parent);
        }
        else {
            pi.setParent((Element) parent);
        }

        ensureCapacity(size+1);
        if( index==size ) {
            elementData[size++] = pi;
        } else {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = pi;
            size++;
        }
        modCount++;
    }

    /**
     * Check and add the <code>CDATA</code> to this list at
     * the given index.
     *
     * @param index index where to add <code>CDATA</code>
     * @param cdata <code>CDATA</code> to add
     */
    protected void add(int index, CDATA cdata) {
        if (cdata == null) {
            throw new IllegalAddException("Cannot add null object");
        }

        if (parent instanceof Document) {
            throw new IllegalAddException(
                          "A CDATA is not allowed at the document root");
        }

        if (cdata.getParent() != null) {
            throw new IllegalAddException(
                          "The CDATA already has an existing parent \"" +
                          cdata.getParent().getQualifiedName() + "\"");
        }

        if (index<0 || index>size) {
            throw new IndexOutOfBoundsException("Index: " + index +
                                                " Size: " + size());
        }

        cdata.setParent((Element) parent);

        ensureCapacity(size+1);
        if( index==size ) {
            elementData[size++] = cdata;
        } else {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = cdata;
            size++;
        }
        modCount++;
    }

    /**
     * Check and add the <code>Text</code> to this list at
     * the given index.
     *
     * @param index index where to add <code>Text</code>
     * @param text <code>Text</code> to add
     */
    protected void add(int index, Text text) {
        if (text == null) {
            throw new IllegalAddException("Cannot add null object");
        }

        if (parent instanceof Document) {
            throw new IllegalAddException(
                          "A Text not allowed at the document root");
        }

        if (text.getParent() != null) {
            throw new IllegalAddException(
                          "The Text already has an existing parent \"" +
                          text.getParent().getQualifiedName() + "\"");
        }

        if (index<0 || index>size) {
            throw new IndexOutOfBoundsException("Index: " + index +
                                                " Size: " + size());
        }

        text.setParent((Element) parent);

        ensureCapacity(size+1);
        if( index==size ) {
            elementData[size++] = text;
        } else {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = text;
            size++;
        }
        modCount++;
    }

    /**
     * Check and add the <code>EntityRef</code> to this list at
     * the given index.
     *
     * @param index index where to add <code>Entity</code>
     * @param entity <code>Entity</code> to add
     */
    protected void add(int index, EntityRef entity) {
        if (entity == null) {
            throw new IllegalAddException("Cannot add null object");
        }

        if (parent instanceof Document) {
            throw new IllegalAddException(
                        "An EntityRef is not allowed at the document root");
        }

        if (entity.getParent() != null) {
            throw new IllegalAddException(
                          "The EntityRef already has an existing parent \"" +
                          entity.getParent().getQualifiedName() + "\"");
        }

        if (index<0 || index>size) {
            throw new IndexOutOfBoundsException("Index: " + index +
                                                " Size: " + size());
        }

        entity.setParent((Element) parent);

        ensureCapacity(size+1);
        if( index==size ) {
            elementData[size++] = entity;
        } else {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = entity;
            size++;
        }
        modCount++;
    }

    /**
     * Add the specified collecton to the end of this list.
     *
     * @param collection The collection to add to the list.
     * @return <code>true</code> if the list was modified as a result of
     *                           the add.
     */
    public boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }

    /**
     * Inserts the specified collecton at the specified position in this list.
     * Shifts the object currently at that position (if any) and any
     * subsequent objects to the right (adds one to their indices).
     *
     * @param index The offset to start adding the data in the collection
     * @param collection The collection to insert into the list.
     * @return <code>true</code> if the list was modified as a result of
     *                           the add.
     * throws IndexOutOfBoundsException if index < 0 || index > size()
     */
    public boolean addAll(int index, Collection collection) {
        if (index<0 || index>size) {
            throw new IndexOutOfBoundsException("Index: " + index +
                                                " Size: " + size());
        }

        if ((collection == null) || (collection.size() == 0)) {
            return false;
        }

        int count = 0;
        try {
            Iterator i = collection.iterator();
            while (i.hasNext()) {
                Object obj = i.next();
                add(index + count, obj);
                count++;
            }
        }
        catch (RuntimeException exception) {
            for (int i = 0; i < count; i++) {
                remove(index);
            }
            throw exception;
        }

        return true;
    }

    /**
     * Clear the current list.
     */
    public void clear() {
        if (elementData != null) {
            for (int i = 0; i < size; i++) {
                Object obj = elementData[i];
                removeParent(obj);
            }
            elementData = null;
            size = 0;
        }
        modCount++;
    }

    /**
     * Clear the current list and set it to the contents
     * of the <code>Collection</code>.
     * object.
     *
     * @param collection The collection to use.
     */
    protected void clearAndSet(Collection collection) {
        Object[] old = elementData;
        int oldSize = size;

        elementData = null;
        size = 0;

        if ((collection != null) && (collection.size() != 0)) {
            ensureCapacity(collection.size());
            try {
                addAll(0, collection);
            }
            catch (RuntimeException exception) {
                elementData = old;
                size = oldSize;
                throw exception;
            }
        }

        if (old != null) {
            for (int i = 0; i < oldSize; i++) {
                removeParent(old[i]);
            }
        }
        modCount++;
    }

    /**
     * Increases the capacity of this <code>ContentList</code> instance,
     * if necessary, to ensure that it can hold at least the number of
     * items specified by the minimum capacity argument.
     *
     * @param minCapacity the desired minimum capacity.
     */
    protected void ensureCapacity(int minCapacity) {
        if( elementData==null ) {
            elementData = new Object[INITIAL_ARRAY_SIZE];
        } else {
            int oldCapacity = elementData.length;
            if (minCapacity > oldCapacity) {
                Object oldData[] = elementData;
                int newCapacity = (oldCapacity * 3)/2 + 1;
                if (newCapacity < minCapacity)
                    newCapacity = minCapacity;
                elementData = new Object[newCapacity];
                System.arraycopy(oldData, 0, elementData, 0, size);
            }
        }
    }

    /**
     * Return the object at the specified offset.
     *
     * @param index The offset of the object.
     * @return The Object which was returned.
     */
    public Object get(int index) {
        if (index<0 || index>=size) {
            throw new IndexOutOfBoundsException("Index: " + index +
                                                " Size: " + size());
        }
        return elementData[index];
    }

    /**
     * Return a view of this list based on the given filter.
     *
     * @param filter <code>Filter</code> for this view.
     * @return a list representing the rules of the <code>Filter</code>.
     */
    protected List getView(Filter filter) {
        return new FilterList(filter);
    }

    /**
     * Return the index of the first Element in the list.  If the parent
     * is a <code>Document</code> then the element is the root element.
     * If the list contains no Elements, it returns -1.
     *
     * @return index of first element, or -1 if one doesn't exist
     */
    protected int indexOfFirstElement() {
        if( elementData!=null ) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] instanceof Element) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Remove the object at the specified offset.
     *
     * @param index The offset of the object.
     * @return The Object which was removed.
     */
    public Object remove(int index) {
        if (index<0 || index>=size)
            throw new IndexOutOfBoundsException("Index: " + index +
                                                 " Size: " + size());

        Object old = elementData[index];
        removeParent(old);
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,numMoved);
        elementData[--size] = null; // Let gc do its work
        modCount++;
        return old;
    }


    /** Remove the parent of a Object */
    private void removeParent(Object obj) {
        if (obj instanceof Element) {
            Element element = (Element) obj;
            element.setParent(null);
        }
        else if (obj instanceof Text) {
            Text text = (Text) obj;
            text.setParent(null);
        }
        else if (obj instanceof Comment) {
            Comment comment = (Comment) obj;
            comment.setParent(null);
        }
        else if (obj instanceof ProcessingInstruction) {
            ProcessingInstruction pi = (ProcessingInstruction) obj;
            pi.setParent(null);
        }
        else if (obj instanceof EntityRef) {
            EntityRef entity = (EntityRef) obj;
            entity.setParent(null);
        }
        else {
            // Should never happen.
            throw new IllegalArgumentException("Object '" + obj + "' unknown");
        }
    }

    /**
     * Set the object at the specified location to the supplied
     * object.
     *
     * @param index The location to set the value to.
     * @param obj The location to set the value to.
     * @return The object which was replaced.
     * throws IndexOutOfBoundsException if index < 0 || index >= size()
     */
    public Object set(int index, Object obj) {
        if (index<0 || index>=size)
            throw new IndexOutOfBoundsException("Index: " + index +
                                                 " Size: " + size());

        if ((obj instanceof Element) && (parent instanceof Document)) {
            int root = indexOfFirstElement();
            if ((root >= 0) && (root != index)) {
                throw new IllegalAddException(
                  "Cannot add a second root element, only one is allowed");
            }
        }

        Object old = remove(index);
        try {
            add(index, obj);
        }
        catch (RuntimeException exception) {
            add(index, old);
            throw exception;
        }
        return old;
    }

    /**
     * Return the number of items in this list
     *
     * @return The number of items in this list.
     */
    public int size() {
        return size;
    }

    /**
     * Return this list as a <code>String</code>
     *
     * @return The number of items in this list.
     */
    public String toString() {
        return super.toString();
    }

    /** Give access of ContentList.modCount to FilterList */
    private int getModCount() {
        return modCount;
    }

    /* * * * * * * * * * * * * FilterList * * * * * * * * * * * * * * * */
    /* * * * * * * * * * * * * FilterList * * * * * * * * * * * * * * * */

    /**
     * <code>FilterList</code> represents legal JDOM content, including content
     * for <code>Document</code>s or <code>Element</code>s.
     */

    class FilterList extends AbstractList {

        /** The Filter */
        protected Filter filter;

        /** Current number of items in this view */
        int count = 0;

        /** Expected modCount in our backing list */
        int expected = -1;

        // Implementation Note: Directly after size() is called, expected
        //       is sync'd with ContentList.modCount and count provides
        //       the true size of this view.  Before the first call to
        //       size() or if the backing list is modified outside this
        //       FilterList, both might contain bogus values and should
        //       not be used without first calling size();

        /**
         * Create a new instance of the FilterList with the specified Filter.
         */
        FilterList(Filter filter) {
            this.filter = filter;
        }

        /**
         * Inserts the specified object at the specified position in this list.
         * Shifts the object currently at that position (if any) and any
         * subsequent objects to the right (adds one to their indices).
         *
         * @param index The location to set the value to.
         * @param obj The object to insert into the list.
         * throws IndexOutOfBoundsException if index < 0 || index > size()
         */
        public void add(int index, Object obj) {
            if (filter.matches(obj)) {
                int adjusted = getAdjustedIndex(index);
                ContentList.this.add(adjusted, obj);
                expected++;
                count++;
            }
            else throw new IllegalAddException("Filter won't allow the " +
                                obj.getClass().getName() + 
                                " '" + obj + "' to be added to the list");
        }

        /**
         * Return the object at the specified offset.
         *
         * @param index The offset of the object.
         * @return The Object which was returned.
         */
        public Object get(int index) {
            int adjusted = getAdjustedIndex(index);
            return ContentList.this.get(adjusted);
        }

        public Iterator iterator() {
            return new FilterListIterator(filter, 0);
        }

        public ListIterator listIterator() {
            return new FilterListIterator(filter, 0);
        }

        public ListIterator listIterator(int index) {
            return new FilterListIterator(filter,  index);
        }

        /**
         * Remove the object at the specified offset.
         *
         * @param index The offset of the object.
         * @return The Object which was removed.
         */
        public Object remove(int index) {
            int adjusted = getAdjustedIndex(index);
            Object old = ContentList.this.get(adjusted);
            if (filter.matches(old)) {
                old = ContentList.this.remove(adjusted);
                expected++;
                count--;
            }
            else {
                throw new IllegalAddException("Filter won't allow the " +
                                             (old.getClass()).getName() +
                                             " '" + old + "' (index " + index +
                                             ") to be removed");
            }
            return old;
        }

        /**
         * Set the object at the specified location to the supplied
         * object.
         *
         * @param index The location to set the value to.
         * @param obj The location to set the value to.
         * @return The object which was replaced.
         * throws IndexOutOfBoundsException if index < 0 || index >= size()
         */
        public Object set(int index, Object obj) {
            Object old = null;
            if (filter.matches(obj)) {
                int adjusted = getAdjustedIndex(index);
                old = ContentList.this.get(adjusted);
                if (!filter.matches(old)) {
                    throw new IllegalAddException("Filter won't allow the " +
                                             (old.getClass()).getName() +
                                             " '" + old + "' (index " + index +
                                             ") to be removed");
                }
                old = ContentList.this.set(adjusted, obj);
                expected += 2;
            }
            else {
                throw new IllegalAddException("Filter won't allow index " +
                                              index + " to be set to " +
                                              (obj.getClass()).getName());
            }
            return old;
        }

        /**
         * Return the number of items in this list
         *
         * @return The number of items in this list.
         */
        public int size() {
            // Implementation Note: Directly after size() is called, expected
            //       is sync'd with ContentList.modCount and count provides
            //       the true size of this view.  Before the first call to
            //       size() or if the backing list is modified outside this
            //       FilterList, both might contain bogus values and should
            //       not be used without first calling size();

            if (expected == ContentList.this.getModCount()) {
                return count;
            }

            count = 0;
            for (int i = 0; i < ContentList.this.size(); i++) {
                Object obj = ContentList.this.elementData[i];
                if (filter.matches(obj)) {
                    count++;
                }
            }
            expected = ContentList.this.getModCount();
            return count;
        }

        /**
         * Return the adjusted index
         *
         * @param index Index of in this view.
         * @return True index in backing list
         */
        final private int getAdjustedIndex(int index) {
            int adjusted = 0;
            for (int i = 0; i < ContentList.this.size; i++) {
                Object obj = ContentList.this.elementData[i];
                if (filter.matches(obj)) {
                    if (index == adjusted) {
                        return i;
                    }
                    adjusted++;
                }
            }

            if (index == adjusted) {
                return ContentList.this.size;
            }

            return ContentList.this.size + 1;
        }
    }

    /* * * * * * * * * * * * * FilterListIterator * * * * * * * * * * * */
    /* * * * * * * * * * * * * FilterListIterator * * * * * * * * * * * */

    class FilterListIterator implements ListIterator {

        /** The Filter that applies */
        Filter filter;

        /** The last operation performed */
        int lastOperation;

        /** Initial start index in backing list */
        int initialCursor;

        /** Index in backing list of next object */
        int cursor;

        /** Index in backing list of last object returned */
        int last;

        /** Expected modCount in our backing list */
        int expected;

        /**
         * Default constructor
         */
        FilterListIterator(Filter filter, int start) {
            this.filter = filter;
            initialCursor = initializeCursor(start);
            last = -1;
            expected = ContentList.this.getModCount();
            lastOperation = CREATE;
        }

        /**
         * Returns <code>true</code> if this list iterator has a next element.
         */
        public boolean hasNext() {
            checkConcurrentModification();

            switch(lastOperation) {
            case CREATE:  cursor = initialCursor;
                          break;
            case PREV:    cursor = last;
                          break;
            case ADD:     
            case NEXT:    cursor = moveForward(last + 1);
                          break;
            case REMOVE:  cursor = moveForward(last);
                          break;
            case HASPREV: cursor = moveForward(cursor + 1);
                          break;
            case HASNEXT: break;
            default:      throw new IllegalStateException("Unknown operation");
            }

            if (lastOperation != CREATE) {
                lastOperation = HASNEXT;
            }

            return (cursor < ContentList.this.size()) ? true : false;
        }

        /**
         * Returns the next element in the list.
         */
        public Object next() {
            checkConcurrentModification();

            if (hasNext()) {
                last = cursor;
            }
            else {
                last = ContentList.this.size();
                throw new NoSuchElementException();
            }

            lastOperation = NEXT;
            return ContentList.this.get(last);
        }

        /**
         * Returns <code>true</code> if this list iterator has more
         * elements when traversing the list in the reverse direction.
         */
        public boolean hasPrevious() {
            checkConcurrentModification();

            switch(lastOperation) {
            case CREATE:  cursor = initialCursor - 1;
                          if (cursor >= ContentList.this.size()) {
                               cursor = moveBackward(initialCursor - 1);
                          }
                          break;
            case PREV:
            case REMOVE:  cursor = moveBackward(last - 1);
                          break;
            case HASNEXT: cursor = moveBackward(cursor - 1);
                          break;
            case ADD:
            case NEXT:    cursor = last;
                          break;
            case HASPREV: break;
            default:      throw new IllegalStateException("Unknown operation");
            }

            if (lastOperation != CREATE) {
                lastOperation = HASPREV;
            }

            return (cursor < 0) ? false : true;
        }

        /**
         * Returns the previous element in the list.
         */
        public Object previous() {
            checkConcurrentModification();

            if (hasPrevious()) {
                last = cursor;
            }
            else {
                last = -1;
                throw new NoSuchElementException();
            }

            lastOperation = PREV;
            return ContentList.this.get(last);
        }

        /**
         * Returns the index of the element that would be returned by a
         * subsequent call to <code>next</code>.
         */
        public int nextIndex() {
            checkConcurrentModification();
            hasNext();

            int count = 0;
            for (int i = 0; i < ContentList.this.size(); i++) {
                if (filter.matches(ContentList.this.get(i))) {
                    if (i == cursor) {
                        return count;
                    }
                    count++;
                }
            }
            expected = ContentList.this.getModCount();
            return count;
        }

        /**
         * Returns the index of the element that would be returned by a
         * subsequent call to <code>previous</code>. (Returns -1 if the
         * list iterator is at the beginning of the list.)
         */
        public int previousIndex() {
            checkConcurrentModification();

            if (hasPrevious()) {
                int count = 0;
                for (int i = 0; i < ContentList.this.size(); i++) {
                    if (filter.matches(ContentList.this.get(i))) {
                        if (i == cursor) {
                            return count;
                        }
                        count++;
                    }
                }
            }
            return -1;
        }

        /**
         * Inserts the specified element into the list.
         */
        public void add(Object obj) {
            checkConcurrentModification();

            if (filter.matches(obj)) {
                last = cursor + 1;
                ContentList.this.add(last, obj);
            }
            else {
                throw new IllegalAddException("Filter won't allow add of " +
                                              (obj.getClass()).getName());
            }
            expected = ContentList.this.getModCount();
            lastOperation = ADD;
        }

        /**
         * Removes from the list the last element that was returned by
         * <code>next</code> or <code>previous</code>.
         * the last call to <code>next</code> or <code>previous</code>.
         */
        public void remove() {
            checkConcurrentModification();

            if ((last < 0) || (lastOperation == REMOVE)) {
                throw new IllegalStateException("no preceeding call to " +
                                                "prev() or next()");
            }

            if (lastOperation == ADD) {
                throw new IllegalStateException("cannot call remove() " +
                                                "after add()");
            }

            Object old = ContentList.this.get(last);
            if (filter.matches(old)) {
                ContentList.this.remove(last);
            }
            else throw new IllegalAddException("Filter won't allow " +
                                                (old.getClass()).getName() +
                                                " (index " + last +
                                                ") to be removed");
            expected = ContentList.this.getModCount();
            lastOperation = REMOVE;
        }

        /**
         * Replaces the last element returned by <code>next</code> or
         * <code>previous</code> with the specified element.
         */
        public void set(Object obj) {
            checkConcurrentModification();

            if ((lastOperation == ADD) || (lastOperation == REMOVE)) {
                throw new IllegalStateException("cannot call set() after " +
                                                "add() or remove()");
            }

            if (last < 0) {
                throw new IllegalStateException("no preceeding call to " +
                                                "prev() or next()");
            }

            if (filter.matches(obj)) {
                Object old = ContentList.this.get(last);
                if (!filter.matches(old)) {
                    throw new IllegalAddException("Filter won't allow " +
                                  (old.getClass()).getName() + " (index " +
                                  last + ") to be removed");
                }
                ContentList.this.set(last, obj);
            }
            else {
                throw new IllegalAddException("Filter won't allow index " +
                                              last + " to be set to " +
                                              (obj.getClass()).getName());
            }

            expected = ContentList.this.getModCount();
            // Don't set lastOperation
        }

        /**
         * Returns index in the backing list by moving forward start + 1
         * objects that match our filter.
         */
        private int initializeCursor(int start) {
            if (start < 0) {
                throw new IndexOutOfBoundsException("Index: " + start);
            }

            int count = 0;
            for (int i = 0; i < ContentList.this.size(); i++) {
                Object obj = ContentList.this.get(i);
                if (filter.matches(obj)) {
                    if (start == count) {
                        return i;
                    }
                    count++;
                }
            }

            if (start > count) {
                throw new IndexOutOfBoundsException("Index: " + start +
                                                    " Size: " + count);
            }

            return ContentList.this.size();
        }

        /**
         * Returns index in the backing list of the next object matching
         * our filter, starting at the given index and moving forwards.
         */
        private int moveForward(int start) {
            if (start < 0) {
                start = 0;
            }
            for (int i = start; i < ContentList.this.size(); i++) {
                Object obj = ContentList.this.get(i);
                if (filter.matches(obj)) {
                    return i;
                }
            }
            return ContentList.this.size();
        }

        /**
         * Returns index in the backing list of the next object matching
         * our filter, starting at the given index and moving backwards.
         */
        private int moveBackward(int start) {
            if (start >= ContentList.this.size()) {
                start = ContentList.this.size() - 1;
            }

            for (int i = start; i >= 0; --i) {
                Object obj = ContentList.this.get(i);
                if (filter.matches(obj)) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * Check if are backing list is being modified by someone else.
         */
        private void checkConcurrentModification() {
            if (expected != ContentList.this.getModCount()) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
