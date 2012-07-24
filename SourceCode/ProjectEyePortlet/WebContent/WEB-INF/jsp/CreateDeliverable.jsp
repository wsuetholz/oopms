<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<portlet:defineObjects />
<link rel="icon" href="https://c15027075.ssl.cf2.rackcdn.com/favicon.ico" type="image/x-icon"/>
<link type="text/css" href="../OOPMSPortlet/resource_files/css/screen.css" rel="Stylesheet" />
<link type="text/css" href="../OOPMSPortlet/resource_files/css/ui-lightness/jquery-ui-1.8.21.custom.css" rel="Stylesheet" />	
<link type="text/css" href="../OOPMSPortlet/resource_files/css/common.css" rel="Stylesheet" />	
<link type="text/css" href="../OOPMSPortlet/resource_files/css/uportal.css" rel="Stylesheet" />
<link rel="stylesheet" type="text/css" href="../OOPMSPortlet/resource_files/css/print.css" media="print"/>
<link rel="stylesheet" type="text/css" href="../OOPMSPortlet/resource_files/css/manage.css" media="all"/>				
<link rel="stylesheet" type="text/css" href="../OOPMSPortlet/resource_files/css/datepicker.css" media="all"/>
<link rel="fluid-icon" href="https://c15027075.ssl.cf2.rackcdn.com/images/apple-touch-icon-114x114.png"/>
<script type="text/javascript" src="../OOPMSPortlet/resource_files/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../OOPMSPortlet/resource_files/js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="../OOPMSPortlet/resource_files/js/form-elements.js"></script>
<script type="text/javascript" src="../OOPMSPortlet/resource_files/css/ga.js"></script>
<script language="javascript" type="text/javascript" src="../OOPMSPortlet/resource_files/css/jquery.js"></script>
<script language="javascript" type="text/javascript" src="../OOPMSPortlet/resource_files/css/jquery.cookie.js"></script>
<script language="javascript" type="text/javascript" src="../OOPMSPortlet/resource_files/css/default.js"></script>
<script language="javascript" type="text/javascript" src="../OOPMSPortlet/resource_files/css/manage.js"></script>
<script language="javascript" type="text/javascript" src="../OOPMSPortlet/resource_files/common.js"></script>
<meta name="robots" content="noindex, nofollow"/>
<script type="text/javascript">
    $(document).ready(function() {
	  $('#mainTable2 tr').filter(':has(:checkbox:checked)').addClass('selected').end().click(function(event) {
	    $(this).toggleClass('selected');
	    if (event.target.type !== 'checkbox') {
	      $(':checkbox', this).attr('checked', function() {
	        return !this.checked;
	      });
	    }
	  });
	  $( "#datepicker1" ).datepicker({
	            showOn: "button",
	            buttonImage: "../OOPMSPortlet/resource_files/images/calendar.gif",
	            buttonImageOnly: true
	        });
	        $( "#datepicker2" ).datepicker({
	            showOn: "button",
	            buttonImage: "../OOPMSPortlet/resource_files/images/calendar.gif",
	            buttonImageOnly: true
	        });
	        $( "#datepicker3" ).datepicker({
	            showOn: "button",
	            buttonImage: "../OOPMSPortlet/resource_files/images/calendar.gif",
	            buttonImageOnly: true
	        });
	        $( "#selectable" ).selectable();
	});
    
    </script>
    
    <title>Create Deliverable</title>	
</head>

<body id="portal" class="up fl-theme-mist">

<div id="portalPageBodyInner" class="container">

  <div class="content">
  <!-- begin .navigator -->
	<jsp:include page="Nagivator.jsp" />
	<!-- end .navigator -->
   <div class="fl-widget-titlebar titlebar portlet-titlebar" role="sectionhead">
    	<h2 class="title" >Create Deliverable</h2>
    </div>

<div style="border-style:ridge" class="up-portlet-content-wrapper-inner">	
<portlet:actionURL var="formAction">
  <portlet:param name="action" value="CreateDeliverable" />
  <portlet:param name="projectId" value="${projectId}" />
</portlet:actionURL>
<portlet:renderURL var="renderAction">
    		<portlet:param name="action" value="GoWorkOrder" />
        	<portlet:param name="projectId" value="${projectId}" />
  	</portlet:renderURL>
<form:form name="${portletNamespace}CreateDeliverable" commandName="CreateDeliverableForm" method="post" action="${formAction}"> 
<table class="portlet-table">
      <tr>      
   		<th scope="row">Deliverable</th>
    	<td><form:select  class="SmallCombo" path="deliverable_SelectedValue" items="${deliverable}"/></td>
  	  </tr>
  	  <tr>
        <th scope="row">Planned committed date*</th>
        <td><input maxlength="9" name="plannedCommittedDate" size="9" value="" type="text" id="datepicker1"/>
          (mm/dd/yyyy)</td>
      </tr>
      <tr>
        <th scope="row">Re-planned committed date</th>
        <td><input maxlength="9" name="rePlannedCommittedDate" size="9" value="" type="text" id="datepicker2"/>
          (mm/dd/yyyy)</td>
      </tr>
      <tr>
        <th scope="row">Actual committed date</th>
        <td><input maxlength="9" name="actualCommittedDate" size="9" value="" type="text" id="datepicker3"/>
          (mm/dd/yyyy)</td>
      </tr>
      <tr>
        <th scope="row">Status</th>
        <td><form:select  class="SmallCombo" path="status_SelectedValue" items="${status}"/></td>
      </tr>
      <tr>
        <th scope="row">Note</th>
        <td><textarea rows="10" cols="70" name="note"></textarea></td>
      </tr>
     </table>   
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CreateDeliverable", "${formAction}")'>Create</button>
	<button type="reset" class="button blue small">Reset</button>
	<button type="button" class="button blue small" onclick='submitAction("${portletNamespace}CreateDeliverable", "${renderAction}")'>Cancel</button>	
	</a>	
</form:form>	
</div>

  <!-- end .content --></div>
  <!-- end .container --></div>
</body>
</html>