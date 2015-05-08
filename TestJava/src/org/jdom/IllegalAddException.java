/*-- 

 $Id: IllegalAddException.java,v 1.3 2011/05/10 12:24:16 sbhattac Exp $

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
    from the JDOM Project Management <request_AT_jdom_DOT_org>.
 
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
 DISCLAIMED.  IN NO EVENT SHALL THE JDOM AUTHORS OR THE PROJECT
 CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
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

/**
 * <code>IllegalAddException</code> is thrown when trying to add a
 * illegal object to a JDOM construct.
 *
 * @author Brett McLaughlin
 * @version $Revision: 1.3 $, $Date: 2011/05/10 12:24:16 $
 */
public class IllegalAddException extends IllegalArgumentException {

    private static final String CVS_ID = 
      "@(#) $RCSfile: IllegalAddException.java,v $ $Revision: 1.3 $ $Date: 2011/05/10 12:24:16 $ $Name: EZTO_HA_2011 $";

    /**
     * This will create an <code>Exception</code> indicating
     * that the addition of the <code>{@link Attribute}</code>
     * to the <code>{@link Element}</code> is illegal.
     *
     * @param base <code>Element</code> that <code>Attribute</code>
     *        couldn't be added to
     * @param added <code>Attribute</code> that could not be added
     * @param reason cause of the problem
     */
    public IllegalAddException(Element base, Attribute added, String reason) {
        super(new StringBuffer()
              .append("The attribute \"")
              .append(added.getQualifiedName())
              .append("\" could not be added to the element \"")
              .append(base.getQualifiedName())
              .append("\": ")
              .append(reason)
              .toString());
    }

    /**
     * This will create an <code>Exception</code> indicating
     * that the addition of the <code>{@link Element}</code>
     * to parent is illegal.
     *
     * @param base <code>Element</code> that the child
     *        couldn't be added to
     * @param added <code>Element</code> that could not be added
     * @param reason cause of the problem
     */
    public IllegalAddException(Element base, Element added, String reason) {
        super(new StringBuffer()
              .append("The element \"")
              .append(added.getQualifiedName())
              .append("\" could not be added as a child of \"")
              .append(base.getQualifiedName())
              .append("\": ")
              .append(reason)
              .toString());
    }

    /**
     * This will create an <code>Exception</code> indicating
     * that the addition of the <code>{@link Element}</code>
     * to the <code>{@link Document}</code> is illegal.
     *
     * @param base <code>Document</code> that the <code>Element</code>
     *        couldn't be added to
     * @param added <code>Element</code> that could not be added
     * @param reason cause of the problem
     */
    public IllegalAddException(Document base, Element added, String reason) {
        super(new StringBuffer()
              .append("The element \"")
              .append(added.getQualifiedName())
              .append("\" could not be added as the root of the document: ")
              .append(reason)
              .toString());
    }

    /**
     * This will create an <code>Exception</code> indicating
     * that the addition of the <code>{@link ProcessingInstruction}</code>
     * to the <code>{@link Element}</code> is illegal.
     *
     * @param base <code>Element</code> that the
     *              <code>ProcessingInstruction</code> couldn't be added to
     * @param added <code>ProcessingInstruction</code> that could not be added
     * @param reason cause of the problem
     */
    public IllegalAddException(Element base, ProcessingInstruction added,
                               String reason) {
        super(new StringBuffer()
              .append("The PI \"")
              .append(added.getTarget())
              .append("\" could not be added as content to \"")
              .append(base.getQualifiedName())
              .append("\": ")
              .append(reason)
              .toString());
    }

    /**
     * This will create an <code>Exception</code> indicating
     * that the addition of the <code>{@link ProcessingInstruction}</code>
     * to the <code>{@link Document}</code> is illegal.
     *
     * @param base <code>Document</code> that the
     *        <code>ProcessingInstruction</code> couldn't be added to
     * @param added <code>ProcessingInstruction</code> that could not be added
     * @param reason cause of the problem
     */
    public IllegalAddException(Document base, ProcessingInstruction added,
                               String reason) {
        super(new StringBuffer()
              .append("The PI \"")
              .append(added.getTarget())
              .append("\" could not be added to the top level of the document: ")
              .append(reason)
              .toString());
    }

    /**
     * This will create an <code>Exception</code> indicating
     * that the addition of the <code>{@link Comment}</code>
     * to the <code>{@link Element}</code> is illegal.
     *
     * @param base <code>Element</code> that the <code>Comment</code>
     *             couldn't be added to
     * @param added <code>Comment</code> that could not be added
     * @param reason cause of the problem
     */
    public IllegalAddException(Element base, Comment added, String reason) {
        super(new StringBuffer()
              .append("The comment \"")
              .append(added.getText())
              .append("\" could not be added as content to \"")
              .append(base.getQualifiedName())
              .append("\": ")
              .append(reason)
              .toString());
    }


    /**
     * This will create an <code>Exception</code> indicating
     * that the addition of the <code>{@link CDATA}</code>
     *
     * @param base <code>Element</code> that the <code>CDATA</code>
     *             couldn't be added to
     * @param added <code>CDATA</code> that could not be added
     * @param reason cause of the problem
     */
    public IllegalAddException(Element base, CDATA added, String reason) {
        super(new StringBuffer()
              .append("The CDATA \"")
              .append(added.getText())
              .append("\" could not be added as content to \"")
              .append(base.getQualifiedName())
              .append("\": ")
              .append(reason)
              .toString());
    }


    /**
     * This will create an <code>Exception</code> indicating
     * that the addition of the <code>{@link Text}</code>
     * to the <code>{@link Element}</code> is illegal.
     *
     * @param base <code>Element</code> that the <code>Comment</code>
     *             couldn't be added to
     * @param added <code>Text</code> that could not be added
     * @param reason cause of the problem
     */
    public IllegalAddException(Element base, Text added, String reason) {
        super(new StringBuffer()
              .append("The Text \"")
              .append(added.getText())
              .append("\" could not be added as content to \"")
              .append(base.getQualifiedName())
              .append("\": ")
              .append(reason)
              .toString());
    }

    /**
     * This will create an <code>Exception</code> indicating
     * that the addition of the <code>{@link Comment}</code>
     * to the <code>{@link Document}</code> is illegal.
     *
     * @param base <code>Document</code> that the <code>Comment</code>
     *             couldn't be added to
     * @param added <code>Comment</code> that could not be added
     * @param reason cause of the problem
     */
    public IllegalAddException(Document base, Comment added, String reason) {
        super(new StringBuffer()
              .append("The comment \"")
              .append(added.getText())
              .append("\" could not be added to the top level of the document: ")
              .append(reason)
              .toString());
    }

    /**
     * This will create an <code>Exception</code> indicating
     * that the addition of the <code>{@link EntityRef}</code>
     * to the <code>{@link Element}</code> is illegal.
     *
     * @param base <code>Element</code> that the <code>EntityRef</code>
     *             couldn't be added to
     * @param added <code>EntityRef</code> reference that could not be added
     * @param reason cause of the problem
     */
    public IllegalAddException(Element base, EntityRef added, String reason) {
        super(new StringBuffer()
              .append("The entity reference\"")
              .append(added.getName())
              .append("\" could not be added as content to \"")
              .append(base.getQualifiedName())
              .append("\": ")
              .append(reason)
              .toString());
    }

    /**
     * This will create an <code>Exception</code> indicating
     * that the addition of the <code>{@link Namespace}</code>
     * to the <code>{@link Element}</code> is illegal.
     *
     * @param base <code>Element</code> that the <code>Namespace</code>
     *             couldn't be added to
     * @param added <code>Namespace</code> that could not be added
     * @param reason cause of the problem
     */
    public IllegalAddException(Element base, Namespace added, String reason) {
        super(new StringBuffer()
              .append("The namespace xmlns")
              .append((added.getPrefix() == null ||
                       added.getPrefix().equals("")) ? "=" 
                                   : ":" + added.getPrefix() + "=")
              .append("\"")
              .append(added.getURI())
              .append("\" could not be added as a namespace to \"")
              .append(base.getQualifiedName())
              .append("\": ")
              .append(reason)
              .toString());
    }

    /**
     * This will create an <code>Exception</code> indicating
     * that the addition of the <code>{@link DocType}</code>
     * to the <code>{@link Document}</code> is illegal.
     *
     * @param base <code>Document</code> that the <code>DocType</code>
     *             couldn't be added to
     * @param added <code>DocType</code> that could not be added
     * @param reason cause of the problem
     */
    public IllegalAddException(Document base, DocType added, String reason) {
        super(new StringBuffer()
              .append("The DOCTYPE ")
              .append(added.toString())
              .append(" could not be added to the document: ")
              .append(reason)
              .toString());
    }

    /**
     * This will create an <code>Exception</code> with the specified
     * error message.
     *
     * @param reason cause of the problem
     */
    public IllegalAddException(String reason) {
        super(reason);
    }
}
