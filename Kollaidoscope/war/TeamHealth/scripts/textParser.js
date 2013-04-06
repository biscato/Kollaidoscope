var biscatoTexts;
var biscatoXmlDoc;

$( document ).delegate("#start", "pageinit", function() {
    loadTextsXML();
    parseTexts();
    fillUITextsForStartPage();
});

function fillUITextsForStartPage(){
    $("#startTitle").html(getText("startTitle"));
    $("#myVotesText").html(getText("myVotes"));
    $("#myDataText").html(getText("myData"));
    $("#myDataTextBack").html(getText("back"));
    $("#myDataTextHeader").html(getText("myData"));
    $("#myDataTextFooter").html(getText("myData"));
    //TODO: add all other UI elements
}

function loadTextsXML(){

    var xmlPath = "resources/strings.xml";
    var xhttp;

    if (window.XMLHttpRequest){
        xhttp=new XMLHttpRequest();
    }
    else{
        xhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xhttp.open("GET",xmlPath,false);
    xhttp.send();
    biscatoXmlDoc = xhttp.responseXML;
}

function parseTexts(){

    biscatoTexts = new Array();
    var lang = "en";

    for(var i in biscatoXmlDoc.documentElement.childNodes){
        if(biscatoXmlDoc.documentElement.childNodes[i].nodeName != "#text" && biscatoXmlDoc.documentElement.childNodes[i].nodeName != undefined){
            var additionalTextItem = new Object();
            additionalTextItem.key = biscatoXmlDoc.documentElement.childNodes[i].tagName;
            additionalTextItem.value = biscatoXmlDoc.documentElement.childNodes[i].getElementsByTagName(lang)[0].firstChild.nodeValue;
            biscatoTexts.push(additionalTextItem);
        }
    }
    //Using documentElement Properties
    //Output texts
    /*alert("XML Root Tag Name: " + xmlDoc.documentElement.tagName);

    //Using firstChild Properties
    //Output year
    alert("First Child: " + xmlDoc.documentElement.childNodes[1].firstChild.tagName);

    //Using lastChild Properties
    //Output average
    alert("Last Child: " + xmlDoc.documentElement.childNodes[1].lastChild.tagName);

    //Using nodeValue and Attributes Properties
    //Here both the statement will return you the same result
    //Output 001
    alert("Node Value: " + xmlDoc.documentElement.childNodes[0].attributes[0].nodeValue);
    alert("Node Value: " + xmlDoc.documentElement.childNodes[0].attributes.getNamedItem("id").nodeValue);

    //Using getElementByTagName Properties
    //Here both the statement will return you the same result
    //Output 2000
    alert("getElementsByTagName: " + xmlDoc.getElementsByTagName("year")[0].attributes.getNamedItem("id").nodeValue);

    //Using text Properties
    //Output John
    alert("Text Content for Employee Tag: " + xmlDoc.documentElement.childNodes[0].text);

    //Using hasChildNodes Properties
    //Output True
    alert("Checking Child Nodes: " + xmlDoc.documentElement.childNodes[0].hasChildNodes);
*/
}

function getText(key){
    if(biscatoTexts == null || biscatoTexts == undefined){
        loadTextsXML();
        parseTexts();
    }

    for(var i in biscatoTexts){
        if(biscatoTexts[i].key == key)
            return biscatoTexts[i].value;
    }
}
