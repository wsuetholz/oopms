<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="portlet2" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="javax.portlet.PortletSession"%>
<portlet2:defineObjects />
<portlet:defineObjects />

<title>Timesheet System : Update timesheet</title>
<jsp:include page="header.jsp" />

<script type="text/javascript">
            function fnFeaturesInit ()
            {
                /* Not particularly modular this - but does nicely :-) */
                $('ul.limit_length>li').each( function(i) {
                    if ( i > 10 ) {
                        this.style.display = 'none';
                    }
                } );
                
                $('ul.limit_length').append( '<li class="css_link">Show more<\/li>' );
                $('ul.limit_length li.css_link').click( function () {
                    $('ul.limit_length li').each( function(i) {
                        if ( i > 5 ) {
                            this.style.display = 'list-item';
                        }
                    } );
                    $('ul.limit_length li.css_link').css( 'display', 'none' );
                } );
            }
            
            $(document).ready( function() {

                $( "input[id$=datepicker]" ).datepicker({
                    showOn: "button",
                    buttonImage: "/TimesheetPortlet/resource_files/images/calendar.gif",
                    buttonImageOnly: true
                });
                    
                           
                fnFeaturesInit();
                $('#mainTable2').dataTable( {
                    "bFilter": true,
                    "bSort": true,
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers"
                } );
              
            } );
        </script>
</head>
<body class="">

<div class="bg">

<div id="content" class="content loggedin">

<div class="container "><portlet:actionURL
  var="updateTimesheeetAction">
  <portlet:param name="action" value="updateTimesheet" />
</portlet:actionURL> 
<portlet:actionURL
  var="rejectTimesheeetAction">
  <portlet:param name="action" value="rejectTimesheet" />
</portlet:actionURL> 
<portlet:actionURL
  var="UpdateTimesheetAction">
  <portlet:param name="action" value="UpdateTimesheet" />
</portlet:actionURL> 
<portlet:actionURL var="backAction">
  <portlet:param name="action" value="init" />
</portlet:actionURL>
<form:form onsubmit='return validate("validate");' name="UpdateTimesheet" method="post" commandName="timesheetForm"
  action="${timesheetFormAction}"> 
  
 <c:if test="${not empty timesheetErrorList}">
        <c:forEach var="timesheet" varStatus="status" items="${timesheetErrorList}">
            <label id="noSelect" style="display: inline; color: red;">${timesheet}</label>
        </c:forEach>
      </c:if> 
<table id="mainTable2" class="display dataTable"  cellpadding="0" cellspacing="0"  border="0" >
 <form:errors path="*" cssStyle="color:red;" />
  <thead>
    <tr>      
      <th style="width: 120px;">Date</th>
      <th >Project </th>
      <th >Work</th>
      <th >Process</th>
      <th >Time</th>
      <th >Description</th>   
       <c:if test="${updateFlag!='true' }"> 
           <c:if test="${ROLE=='Project Manager' }">       
      <th >Comment</th>
      </c:if>
      </c:if>
       
    </tr>
  </thead>
  <tbody align="center">
  
      <c:forEach var="timesheet" varStatus="status" items="${timesheetList}">
        <tr>        
          <td><p style="display: block; margin: 0px;padding: 0px;"><input style="width: 90px;" id="${status.index}datepicker" name="timesheetList[${status.index}].occurDateString" value="${timesheet.occurDateString}"/></p></td>
          <td><form:select cssClass="validate" 
          path="timesheetList[${status.index}].project.projectId" multiple="single" style="width: 100px;">
          <form:options items="${projectMap}" /> 
         </form:select></td>
         <td><form:select 
          path="timesheetList[${status.index}].towId" multiple="single" style="width: 80px;">
          <form:options items="${towMap}" />
        </form:select></td>
        <td><form:select 
          path="timesheetList[${status.index}].processId" multiple="single" style="width: 170px;">
          <form:options items="${processMap}" />
        </form:select></td>
         <td><input style="width: 30px;" name="timesheetList[${status.index}].duration" value="${timesheet.durationString}"/></td>
          <td><input style="width: 200px;" name="timesheetList[${status.index}].description" value="${timesheet.description}"/></td>
         <c:if test="${updateFlag!='true' }"> 
           <c:if test="${ROLE=='Project Manager' }">     
              <td><input style="width: 100px;" name="timesheetList[${status.index}].rcomment" value="${timesheet.rcomment}"/></td>
             </c:if>             
           </c:if>
         <!--
         
          <td><input name="timesheetList[${status.index}].towName" value="${timesheet.towName}"/></td>
          <td><input name="timesheetList[${status.index}].processName" value="${timesheet.processName}"/></td>
           <td><input name="timesheetList[${status.index}].duration" value="${timesheet.duration}"/></td>
          <td><input name="timesheetList[${status.index}].description" value="${timesheet.description}"/></td>
        
        --></tr>
      </c:forEach>

    

  </tbody>
</table>
<br>
<p>
 <c:if test="${rejectFlag=='true' }">
  <input onclick='submitAction("UpdateTimesheet", "${rejectTimesheeetAction}")' name="Submit" value="Reject" class="button blue small" type="button"/>
 </c:if>
  <c:if test="${rejectFlag!='true' }">
 <input onclick='submitAction("UpdateTimesheet", "${updateTimesheeetAction}")' name="Submit" value="Update" class="button blue small" type="button"/>
 </c:if>
<input onclick='submitAction("UpdateTimesheet", "${backAction}")' name="Submit" value="Back" class="button grey small" type="button"/></p>
</form:form>

</div>
</div>
</div>





</body>
</html>