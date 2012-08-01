/**
 * Licensed to Open-Ones Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones Group licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/**
 * Submit the form.
 * @param formName Identifier of the form. It combines the namespace of portlet and logical name of form.
 * @param eventId Code of event.
 * @return
 */
function submitAction(formName, actionUrl) {
  
var frm = document.forms[formName];
	
	frm.action = actionUrl;
	
	frm.submit();
    
	
}


    
   

function validate(checkedClass){
    alert("validate");
    var requiredList = $(checkedClass);
    alert(requiredList.length);
    var isSubmited=true;
    var isFocused=false;
    for(var i=0; i<requiredList.length; i++){
        var inputVal = requiredList[i].children[0].value.trim();
        alert(inputVal);
        var DOM = requiredList[i].children[0];
        if(inputVal == '' || inputVal == '0'){
            alert("Please input date");
            DOM.focus();
            return false;
        }
        return true;
    }
    
}