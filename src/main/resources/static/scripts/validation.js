// Defining a function to display error message
function printError(elemId, hintMsg) {
    document.getElementById(elemId).innerHTML = hintMsg;
}


function validateForm() {
    // Retrieving the values of form elements
    var sender = document.getElementById("sender").value;
    var url = document.getElementById("urlAddress").value;

	// NOTE: Reading the value of hobbies (checkbox inputs) is different than the above approach (search on the Internet to learn).
    

    // Defining error variables with a default value
    var senderErr = true;
    var urlErr = true;



    // Validate name

    var regex = /^[a-zA-Z\s]+$/;
    if (regex.test(sender) == false) {
        printError("senderErr", "Please enter a valid name");
    } else {
        printError("senderErr", "");
        senderErr = false;
    }
    
    // validate url
   
    var oRegUrl = new RegExp();
    //aa.bb.com   and no more than 2000
    oRegUrl.compile("^[A-Za-z]+://[A-Za-z0-9-_]+\\.[A-Za-z0-9-_%&\?\/.=]+$");
    if(oRegUrl.test(url)==false){
        printError("urlErr", "Please enter a valid url");
    }else if (url.length>2000) {
        printError("urlErr", "URL should be maximum 2000 characters");
    }else 
    {
        printError("urlErr","");
        urlErr=false;
    }

    
    // Prevent the form from being submitted if there are any errors
    if (senderErr||urlErr) {
        return false;
    } else {
        // Creating a string from input data for preview
        var dataPreview = "You've entered the following details: \n" +
            "Full Name: " + sender + "\n"+
            "url: "+urlErr+"\n";
        // Display input data in a dialog box before submitting the form
        alert(dataPreview);
    }
};