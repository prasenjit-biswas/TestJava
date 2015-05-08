/*-- 

 $Id: ElementFilter.java,v 1.3 2011/05/10 12:24:20 sbhattac Exp $

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

package org.jdom.filter;

import org.jdom.*;

/**
 * The <code>ElementFilter</code> when applied to a <code>FilterList</code>
 * will only allow <code>Elements</code> to be visible.
 *
 * @author Jools Enticknap
 * @author Bradley S. Huffman
 * @version $Revision: 1.3 $, $Date: 2011/05/10 12:24:20 $
 */
public class ElementFilter implements Filter {

    private static final String CVS_ID = 
      "@(#) $RCSfile: ElementFilter.java,v $ $Revision: 1.3 $ $Date: 2011/05/10 12:24:20 $ $Name: EZTO_HA_2011 $";

    /** The element name */
    protected String name;

    /** The element namespace */
    protected Namespace namespace;
    
    /**
     * Select only the Elements.
     */
    public ElementFilter() {}

    /**
     * Select only the Elements with the supplied name in any Namespace.
     *
     * @param name   The name of the Element.
     */
    public ElementFilter(String name) {
        this.name   = name;
    }

    /**
     * Select only the Elements with the supplied Namespace.
     *
     * @param namespace The namespace the Element lives in.
     */
    public ElementFilter(Namespace namespace) {
        this.namespace = namespace;
    }

    /**
     * Select only the Elements with the supplied name and Namespace.
     *
     * @param name   The name of the Element.
     * @param namespace The namespace the Element lives in.
     */
    public ElementFilter(String name, Namespace namespace) {
        this.name   = name;
        this.namespace = namespace;
    }

    /**
     * Check to see if the object matches a predefined set of rules.
     *
     * @param obj The object to verify.
     * @return <code>true</code> if the objected matched a predfined 
     *           set of rules.
     */
    public boolean matches(Object obj) {
        if (obj instanceof Element) {
            Element element = (Element) obj;
            if (name == null) {
                if (namespace == null) {
                    return true;
                }
                else {
                    return namespace.equals(element.getNamespace());
                }
            }
            else {
                if (name.equals(element.getName())) {
                    if (namespace == null) {
                        return true;
                    }
                    else {
                        return namespace.equals(element.getNamespace());
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns true if object is instance of ElementFilter and has
     * the same parent Element, name, and namespace as this filter.
     *
     * @return <code>true</code> if the Filters are equal
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj instanceof ElementFilter) {
            ElementFilter filter = (ElementFilter) obj;
            if (namespace == filter.namespace) {
                if (name == filter.name) {
                    return true;
                }
                else {
                    return (name == null) ? false : name.equals(filter.name);
                }
            }
        }
        return false;
    }
}
