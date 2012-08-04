<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License. You may obtain a
    copy of the License at:

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on
    an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied. See the License for the
    specific language governing permissions and limitations
    under the License.

--%>

<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!-- START: VALUES BEING PASSED FROM BACKEND -->
<portlet:actionURL var="queryUrl">
	<portlet:param name="execution" value="${flowExecutionKey}" />
</portlet:actionURL>
<!-- END: VALUES BEING PASSED FROM BACKEND -->

<!--
PORTLET DEVELOPMENT STANDARDS AND GUIDELINES
| For the standards and guidelines that govern
| the user interface of this portlet
| including HTML, CSS, JavaScript, accessibilty,
| naming conventions, 3rd Party libraries
| (like jQuery and the Fluid Skinning System)
| and more, refer to:
| http://www.ja-sig.org/wiki/x/cQ
-->
    
<!-- Portlet -->
<div class="fl-widget portlet ptl-mgr view-basicinfo" role="section">

	<!-- Portlet Titlebar -->
  <div class="fl-widget-titlebar titlebar portlet-titlebar" role="sectionhead">
  	<h2 class="title" role="heading">
        <spring:message code="${ completed ? 'edit.portlet' : 'register.new.portlet' }"/>
    </h2>
  </div> <!-- end: portlet-titlebar -->
  
  <!-- Portlet Content -->
  <div class="fl-widget-content content portlet-content" role="main">
    
    <form:form modelAttribute="portlet" action="${queryUrl}" method="POST">
	
	<!-- Portlet Messages -->
    <spring:hasBindErrors name="portlet">
        <div class="portlet-msg-error portlet-msg error" role="alert">
            <form:errors path="*" element="div"/>
        </div> <!-- end: portlet-msg -->
    </spring:hasBindErrors>
		
    <!-- Portlet Section -->
    <div class="portlet-section" role="region">
      <div class="titlbar">
        <h3 class="title" role="heading"><spring:message code="summary.information"/></h3>
      </div>
      <div class="content">

        <table class="portlet-table" summary="<spring:message code="this.table.lists.portlets.general.settings"/>">
          <thead>
            <tr>
            	<th><spring:message code="option"/></th>
              <th><spring:message code="setting"/></th>
            </tr>
          </thead>
          <tbody>
            <tr>
            	<td class="fl-text-align-right"><spring:message code="portlet.title"/>:</td>
            	<td><form:input path="title"/></td>
            </tr>  
            <tr>
            	<td class="fl-text-align-right"><spring:message code="portlet.name"/>:</td>
            	<td><form:input path="name"/></td>
           </tr>      
            <tr>
            	<td class="fl-text-align-right"><spring:message code="portlet.functional.name"/>:</td>
            	<td><form:input path="fname"/></td>
            </tr>     
            <tr>
            	<td class="fl-text-align-right"><spring:message code="portlet.description"/>:</td>
            	<td><form:input path="description"/></td>
            </tr> 
            <tr>
            	<td class="fl-text-align-right"><spring:message code="portlet.timeout"/>:</td>
            	<td><form:input path="timeout"/>ms</td>
            </tr>  
          </tbody>
        </table>
        
			</div>
		</div> <!-- end: portlet-section -->
    
    <!-- Portlet Section -->
    <div class="portlet-section" role="region">
      <div class="titlebar">
        <h3 class="title" role="heading"><spring:message code="controls"/></h3>
      </div>
      <div class="content">
      
      	<fieldset>
          <legend><spring:message code="portlet.controls"/></legend>
              <form:checkbox path="hasHelp"/>
              <label for="hasHelp"><spring:message code="hasHelp"/></label><br/>

              <form:checkbox path="editable"/>
              <label for="editable"><spring:message code="editable"/></label><br/>

              <form:checkbox path="hasAbout"/>
              <label for="hasAbout"><spring:message code="hasAbout"/></label><br/>

        </fieldset>
        
      </div>
    </div> <!-- end: portlet-section -->
    
    <!-- Buttons -->
    <div class="buttons">
      <c:choose>
        <c:when test="${ completed }">
          <input class="button primary" type="submit" value="<spring:message code="review"/>" name="_eventId_review"/>
        </c:when>
        <c:otherwise>
          <input class="button primary" type="submit" value="<spring:message code="continue"/>" name="_eventId_next"/>
          <input class="button" type="submit" value="<spring:message code="back"/>" name="_eventId_back"/>
        </c:otherwise>
      </c:choose>
      <input class="button" type="submit" value="<spring:message code="cancel"/>" name="_eventId_cancel"/>
    </div>
    
    </form:form> <!-- End Form -->
            
	</div> <!-- end: portlet-content -->
        
</div> <!-- end: portlet -->