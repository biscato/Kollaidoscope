var biscatoDataLoggedInUser;
var biscatoDataQuestions = new Array();
var biscatoDataUsersTeamMembers = new Array();
var biscatoDataParticipantsToInvite = new Array();
var biscatoDataAnswers = new Array();

var biscatoProtocol;
var biscatoHost;
var biscatoPort;

var biscatoQuestionaireSubURL = "/teamhealth/questionaire";
var biscatoAnswerSubURL = "/teamhealth/answers";
var biscatoUserSubURL = "/teamhealth/users";
var biscatoSurveySubURL = "/teamhealth/surveys";
var biscatoStatisticsSubURL = "/teamhealth/statistics";
var biscatoTestAnswerSubURL = "/teamhealth/testanswer";
var biscatoTestTeamAndUserSubURL = "/teamhealth/teamAndUsers";

var biscatoCreateSurveyRequestCounter;
var biscatoCreateSurveyResponseCounter;



/*************************************************************************************
*************** Vote section**********************************************************
*************************************************************************************/
$('#vote').live('pagebeforecreate',function(event, ui){
	
	var biscatoQuestionaireURL = getBiscatoQuestionaireURL();
	
    $.ajax(
        {
            type: "GET",
            url: biscatoQuestionaireURL,
            data: "{}",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                biscatoDataQuestions = data.question;
                displayAllQuestions();
            },
            error: function (msg, url, line) {
                alert('error trapped in error: function(msg, url, line)');
                alert('msg = ' + msg + ', url = ' + url + ', line = ' + line);

            }
        });
});

function displayAllQuestions(targetDOMElement){

    var html = "";
    var sliderMinValue = 0;
    var sliderMaxValue = 10;
    var sliderStartValue = 5;
    var questionsCounter = 0;

    for(var i=0; i < biscatoDataQuestions.length; i++){
        questionsCounter++;
        html += "<div>"+questionsCounter+ ") "; //TODO: localize text
        html += "<label>" + biscatoDataQuestions[i].description + "</label></div>"; //TODO: localize text
        html += "<div data-role='fieldcontain'>";
        html += "<input type='range' name='slider-" + i + "' id='slider-" + i + "' value='" + sliderStartValue + "' min='" + sliderMinValue + "' max='" + sliderMaxValue + "' />";
        html += "<label for='slider-" + i + "'></label>"; //TODO: localize text
        html += "</div>";
        html += "<br>";
    }
    $("#voting").append(html).trigger('create');
    //TODO: check this some time: http://www.kelvinluck.com/assets/jquery/ui-slider/slider_test.html
}

function saveVote(){
	
	var biscatoAnswerURL = getBiscatoAnswerURL();
	var votes = getVotesFromUser();
	
	$.ajax(
            {
                type: "POST",
                url: biscatoAnswerURL,
                data: votes,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    answersCreatedSuccess(data.question);
                },
                error: function (msg, url, line) {
                    alert('error trapped in error: function(msg, url, line)');
                    alert('msg = ' + msg + ', url = ' + url + ', line = ' + line);

                }
            });
}

function getVotesFromUser(){

	var answers = new Array();
	
    for(var i=0; i < biscatoDataQuestions.length; i++){
        
        var sliderString = "slider-"+i;
        answers[i] = new Array();
        answers[i]["questionId"] = biscatoDataQuestions[i].id;
        answers[i]["questionText"] = biscatoDataQuestions[i].description;
        answers[i]["questionCategory"] = biscatoDataQuestions[i].category;
        answers[i]["questionCategoryId"] = biscatoDataQuestions[i].categoryId;
        answers[i]["user"] = "janruessel@gmail.com";
        answers[i]["rating"] = document.getElementById(sliderString).value;

    }

	return convertVoteToJSONFormat(answers);
}

function convertVoteToJSONFormat(answers){
	var votes = {
		    answer: []
		};
	
    for(var i in answers){
    	var answer = answers[i];
    	votes.answer.push({
    		"questionId" : answers[i].questionId,
    		"questionText" : answers[i].questionText,
    		"questionCategory" : answers[i].questionCategory,
    		"questionCategoryId" : answers[i].questionCategoryId,
    		"user" : answers[i].user,
    		"rating" : answers[i].rating
    	});
    }
    var myString = JSON.stringify(votes);
    return myString;
}

function answersCreatedSuccess(responseData){
	alert("Answers successfully created");
}
/*************************************************************************************
*************** my past Votes section*************************************************
*************************************************************************************/
$('#myVotes').live('pageshow',function(event, ui){

    var dummyData = [['4',5],['5', 4],['6', 5],['7',7],['8',7],['9',8]];

    $.jqplot('myVotesChart', [dummyData],
        {
//            axesDefaults: {ticks: [0,2,4,6,8,10]},
//            axesDefaults: {ticks: [[2, 0],[4, 2],[6, 4],[5, 6],[8, 8]]},
            axes:{
                xaxis: {
//                    borderColor: "#222",
                    renderer: $.jqplot.DateAxisRenderer,
                    numberTicks:6,
                    tickOptions:{formatString:'%b'},
                    rendererOptions:{tickRenderer: $.jqplot.CanvasAxisTickRenderer}
                },
                yaxis:{min:0, max:10}},
            series:[{color:'#5FAB78'}]
        });
});

/*************************************************************************************
 *************** My Data section**********************************************************
 *************************************************************************************/
$('#myData').live('pagebeforecreate',function(event, ui){

    var biscatoUserURL = getBiscatoUserURL();
    var userId = getUserId();
    biscatoUserURL += "/"+userId;

    $.ajax(
        {
            type: "GET",
            url: biscatoUserURL,
            data: "{}",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                biscatoDataLoggedInUser = data;
                displayUserData(data);
            },
            error: function (msg, url, line) {
                alert('error trapped in error: function(msg, url, line)');
                alert('msg = ' + msg + ', url = ' + url + ', line = ' + line);

            }
        });
});

function displayUserData(data){
    $('#firstName').textinput("disable");
    $('#lastName').textinput("disable");
    $('#email').textinput("disable");
    $('#role').textinput("disable");
    $('#company').textinput("disable");

    $('#firstName').val(data.firstName);
    $('#lastName').val(data.lastName);
    $('#email').val(data.email);
    $('#role').val(data.role);
    $('#company').val(data.company);
}


/*************************************************************************************
*************** Team Results section *************************************************
*************************************************************************************/

$('#teamResults').live('pageshow',function(event, ui){
	// determine time frame for data selection --> TODO
	// get team --> TODO
	// get users for team --> TODO
	// get data for users in specified timeframe --> TODO
	// display chart for retrieved data

    $("#teamResultsTimeframe").bind( "change",function(event, ui){
        handleChangedTimeframeForTeamResults(event, ui);
    });

    loadTeamResults();

});

function handleChangedTimeframeForTeamResults(event, ui){
    var from = getStartTimeFromTimeframe($("#teamResultsTimeframe").val());
    if(from != undefined){
//        var to = new Date().getMilliseconds();
        var to = Date.parse(new Date());
        loadTeamResults(from, to);
    }
}

function getStartTimeFromTimeframe(timeframe){
    var date = new Date();
    switch (timeframe){
        case "1week":
            date.setDate(date.getDate()-7);
            break;
        case "2week":
            date.setDate(date.getDate()-14);
            break;
        case "3week":
            date.setDate(date.getDate()-21);
            break;
        case "4week":
            date.setDate(date.getDate()-28);
            break;
        case "full":
            date.setYear(date.getYear()-1);
            break;
        case "half":
            date.setMonth(date.getMonth()-6);
            break;
        case "all":
            date.setYear(2011);
            break;
        default: //standard
            date.setYear(1980); //TODO: implement a logic to select only "last vote"
            break;
    }

    return Date.parse(date);
}

function loadTeamResults(from, to){
    var biscatoStatisticsURL = getBiscatoStatisticsURL();
    if(from != undefined){
        biscatoStatisticsURL += "?from="+from+"&to="+to;
    }
    $.ajax(
        {
            type: "GET",
            url: biscatoStatisticsURL,
            data: "{}",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                displayTeamResults(data);
            },
            error: function (msg, url, line) {
                alert('error trapped in error: function(msg, url, line)');
                alert('msg = ' + msg + ', url = ' + url + ', line = ' + line);

            }
        });
}

function displayTeamResults(data){
	 
//	BAR CHART, see http://www.jqplot.com/tests/bar-charts.php

	var vision1 	= [parseFloat(data.vision)];
    var innovation2 = [parseFloat(data.innovation)];
    var task3 		= [parseFloat(data.taskOrientation)];
    var security4 	= [parseFloat(data.security)];
    var structure5 	= [parseFloat(data.structure)];
    var virtual6 	= [parseFloat(data.virtualCompetence)];

    var ticks = ["Last Survey"];
    
    $.jqplot('teamResultsChart', [vision1, innovation2, task3, security4, structure5, virtual6], {
        // The "seriesDefaults" option is an options object that will
        // be applied to all series in the chart.
        seriesDefaults:{
            renderer:$.jqplot.BarRenderer,
            rendererOptions: {fillToZero: true}
        },
        // Custom labels for the series are specified with the "label"
        // option on the series option.  Here a series option object
        // is specified for each series.
        series:[
            {label:'Vision'},
            {label:'Innovation'},
            {label:'Task Orientation'},
            {label:'Security'},
            {label:'Structural Approach'},
            {label:'Virtual Competence'}
        ],
        // Show the legend and put it outside the grid, but inside the
        // plot container, shrinking the grid to accomodate the legend.
        // A value of "outside" would not shrink the grid and allow
        // the legend to overflow the container.
        legend: {
            show: true,
            location: 's', //n, ne, e
            rendererOptions:{
                numberRows: 1
            },
//            xoffset:55,
//            yoffset:-100
            placement: 'outsideGrid'
        },
        axes: {
            // Use a category axis on the x axis and use our custom ticks.
            xaxis: {
                renderer: $.jqplot.CategoryAxisRenderer,
                ticks: ticks
            },
            // Pad the y axis just a little so bars can get close to, but
            // not touch, the grid boundaries.  1.2 is the default padding.
            yaxis: {
            	min: 0,
            	max: 10,
                pad: 1.05,
                tickOptions: {formatString: '%d'}
            }
        }
    });
	
	
//	LINE CHART
//    $.jqplot('teamResultsChart',  [[[7,5],[8, 4],[9, 5],[10,7],[11,7],[12,8]]],
//        { title:'Team Votes',
//            axes:{yaxis:{min:0, max:10}},
//            series:[{color:'#5FAB78'}]
//        });	
	
//    var s1 = [200, 600, 700, 1000];
//    var s2 = [460, -210, 690, 820];
//    var s3 = [-260, -440, 320, 200];
//    // Can specify a custom tick Array.
//    // Ticks should match up one for each y value (category) in the series.
//    var ticks = ['May', 'June', 'July', 'August'];
//     
//    var plot1 = $.jqplot('teamResultsChart', [s1, s2, s3], {
//        // The "seriesDefaults" option is an options object that will
//        // be applied to all series in the chart.
//        seriesDefaults:{
//            renderer:$.jqplot.BarRenderer,
//            rendererOptions: {fillToZero: true}
//        },
//        // Custom labels for the series are specified with the "label"
//        // option on the series option.  Here a series option object
//        // is specified for each series.
//        series:[
//            {label:'Hotel'},
//            {label:'Event Regristration'},
//            {label:'Airfare'}
//        ],
//        // Show the legend and put it outside the grid, but inside the
//        // plot container, shrinking the grid to accomodate the legend.
//        // A value of "outside" would not shrink the grid and allow
//        // the legend to overflow the container.
//        legend: {
//            show: true,
//            placement: 'outsideGrid'
//        },
//        axes: {
//            // Use a category axis on the x axis and use our custom ticks.
//            xaxis: {
//                renderer: $.jqplot.CategoryAxisRenderer,
//                ticks: ticks
//            },
//            // Pad the y axis just a little so bars can get close to, but
//            // not touch, the grid boundaries.  1.2 is the default padding.
//            yaxis: {
//                pad: 1.05,
//                tickOptions: {formatString: '$%d'}
//            }
//        }
//    });
}

/*************************************************************************************
 *************** Create New Survey ***************************************************
 *************************************************************************************/
$('#createSurvey').live('pageshow',function(event, ui){
    //determine which teams the user is teamlead of
    biscatoDataParticipantsToInvite = [];
    var leadOfTeams = new Array();
    var biscatoUsersForTeamURL;

    setDefaultStartDate();
    leadOfTeams = getTeamsWhereUserIsLead();
    biscatoCreateSurveyRequestCounter = 0;
    biscatoCreateSurveyResponseCounter = 0;

    for(var i in leadOfTeams){
        //read team list from server with currentUser and the users' TeamId(s)
        biscatoUsersForTeamURL = getBiscatoUsersForTeamURL(leadOfTeams[i]);
        biscatoCreateSurveyRequestCounter++;
        $.ajax(
            {
                type: "GET",
                url: biscatoUsersForTeamURL,
                data: "{}",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    biscatoDataUsersTeamMembers.concat(data.user);
                    handleUsersOfTeamRequest(data);
                },
                error: function (msg, url, line) {
                    alert('error trapped in error: function(msg, url, line)');
                    alert('msg = ' + msg + ', url = ' + url + ', line = ' + line);
                }
            });

    }
});

function handleUsersOfTeamRequest(data){
    biscatoCreateSurveyResponseCounter++;
    for(i in data.user){
        addUserToInvitationList(data.user[i]);
    }
    if(biscatoCreateSurveyRequestCounter == biscatoCreateSurveyResponseCounter){
        displayTeamMembersAsParticipantsToInvite();
    }
}

function displayTeamMembersAsParticipantsToInvite(){
    $("#participantsToInviteListUI").remove();
    var html = "<div id='participantsToInviteListUI'>";
    for(var i in biscatoDataParticipantsToInvite){
        html += "<input type='checkbox' name='name"+i+"' id='checkbox"+i+"' />";
        html += "<label for='checkbox"+i+"'>"+biscatoDataParticipantsToInvite[i]+"</label>";
//        html += "<li data-icon='delete' onclick='removeParticipantFromInvitation(" + biscatoParticipantsToInvite[i] + ")'><a>" + biscatoParticipantsToInvite[i] + "</a></li>";
    }
    html += "<button onclick='removeParticipantFromInvitation()'>remove from invitation list</button></div>"; //TODO: localize text
    $("#participantsToInvite").append(html).trigger('create');
}
//function displayTeamMembersAsParticipantsToInvite(){
//    $("#participantsToInviteListUI").remove();
//    var html = "<div id='participantsToInviteListUI'><ul data-role='listview' data-inset='true' data-theme='c'>";
//    for(var i in biscatoParticipantsToInvite){
//        html += "<li data-icon='delete' onclick='removeParticipantFromInvitation()'><a>" + biscatoParticipantsToInvite[i] + "</a></li>";
////        html += "<li data-icon='delete' onclick='removeParticipantFromInvitation(" + biscatoParticipantsToInvite[i] + ")'><a>" + biscatoParticipantsToInvite[i] + "</a></li>";
//    }
//    html += "</ul><button>remove</button></div>";
//    $("#participantsToInvite").append(html).trigger('create');
//}
function createNewSurvey(){
    alert("create new survey")
}

function removeParticipantFromInvitation(){
    //remove from list UI
    //remove from biscatoParticipantsToAdd to prevent email invitation

    var participantsToRemove = new Array();
    var markedForRemoval;
    var elementToRemoveIndex;
    var domHelper;

    for(var i in biscatoDataParticipantsToInvite){
        domHelper = "#checkbox"+i;
        markedForRemoval = false;
        markedForRemoval = $(domHelper).is(':checked');
        if(markedForRemoval)
            participantsToRemove.push(biscatoDataParticipantsToInvite[i]);
    }

    for(var i=0; i< participantsToRemove.length; i++){
        elementToRemoveIndex = biscatoDataParticipantsToInvite.indexOf(participantsToRemove[i]);
        if(elementToRemoveIndex != -1){
            biscatoDataParticipantsToInvite.splice(elementToRemoveIndex, 1);
        }

    }


    displayTeamMembersAsParticipantsToInvite();
}
function addUserToInvitationList(user){
    //build up list of new invites which need to be invited when new survey is created
    var value = user.firstName;
    value += " "+user.lastName;
    value += " team"; //TODO: display actual team name
    biscatoDataParticipantsToInvite.push(value);
}

function addParticipantToInvitationByEmail(email){
    //build up list of new invites which need to be invited when new survey is created
    var value = $(email).val();
    biscatoDataParticipantsToInvite.push(value);
    displayTeamMembersAsParticipantsToInvite();
}

function getTeamsWhereUserIsLead(){
    var leadOfTeams = new Array();
    leadOfTeams[0] = 1;
    return leadOfTeams;
}

function setDefaultStartDate(){
    $('#fromDate').val(new Date().toJSON().substring(0,10));
}

function createSurvey(){
    var biscatoSurveyURL = getBiscatoSurveyURL();
    var surveyData = getSurveyDataInJSONFormat();
    $.ajax(
        {
            type: "POST",
            url: biscatoSurveyURL,
            data: surveyData,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                questionsCreatedSuccess(data.question);
            },
            error: function (msg, url, line) {
                alert('error trapped in error: function(msg, url, line)');
                alert('msg = ' + msg + ', url = ' + url + ', line = ' + line);

            }
        });
}

function getSurveyDataInJSONFormat(){
    var jsonSurveyData = {
        survey: []
    };

    jsonQuestions.survey.push({
        "startTimestamp" : questionCatalogue[i].language,
        "endTimestamp" : questionCatalogue[i].description,
        "participant" : biscatoDataParticipantsToInvite,
        "teamLead" : questionCatalogue[i].categoryId
    });

    var myString = JSON.stringify(jsonQuestions);
    return myString;
}
/*************************************************************************************
*************** generic Stuff ********************************************************
*************************************************************************************/
function getBaseURL(){
	var baseURL;
	if(biscatoHost == null || biscatoHost == undefined){
		biscatoProtocol = window.location.protocol;
		biscatoHost = window.location.hostname;
		biscatoPort = window.location.port;
	}
	baseURL = biscatoProtocol + "//" + biscatoHost + ":" + biscatoPort;
	return baseURL;
}

function getBiscatoQuestionaireURL(){
	var url;
	url = getBaseURL();
	url += biscatoQuestionaireSubURL;
	return url;
}

function getBiscatoAnswerURL(){
	var url;
	url = getBaseURL();
	url += biscatoAnswerSubURL;
	return url;
}

function getBiscatoStatisticsURL(){
	var url;
	url = getBaseURL();
	url += biscatoStatisticsSubURL;
	return url;
}

function getBiscatoTestAnswerURL(){
    var url;
    url = getBaseURL();
    url += biscatoTestAnswerSubURL;
    return url;
}

function getBiscatoTestTeamAndUserURL(){
    var url;
    url = getBaseURL();
    url += biscatoTestTeamAndUserSubURL;
    return url;
}

function getBiscatoUserURL(){
    var url;
    url = getBaseURL();
    url += biscatoUserSubURL;
    return url;
}

function getBiscatoSurveyURL(){
    var url;
    url = getBaseURL();
    url += biscatoSurveySubURL;
    return url;
}

function getBiscatoUsersForTeamURL(teamid){
    var url = getBiscatoUserURL();
    url += "?teamid="+teamid;
    return url;
}

function getUserId(){
    var userId;
    userId = 1032;
    return userId;
}
/*************************************************************************************
*************** Test Stuff ***********************************************************
*************************************************************************************/

/******* Creation of Questions ******************************/
function createAllQuestions(){
    
	var biscatoQuestionaireURL = getBiscatoQuestionaireURL();
	var questionsInJSONFormat = getTestQuestionsInJSONFormat();
	$.ajax(
            {
                type: "POST",
                url: biscatoQuestionaireURL,
                data: questionsInJSONFormat,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    questionsCreatedSuccess(data.question);
                },
                error: function (msg, url, line) {
                    alert('error trapped in error: function(msg, url, line)');
                    alert('msg = ' + msg + ', url = ' + url + ', line = ' + line);

                }
            });
}

function getTestQuestionsInJSONFormat(){
	
	var questionCatalogue = createQuestions();
	
	var jsonQuestions = {
		    question: []
		};
	
    for(var i in questionCatalogue){
    	var question = questionCatalogue[i];
    	jsonQuestions.question.push({
    		"language" : questionCatalogue[i].language,
    		"description" : questionCatalogue[i].description,
    		"category" : questionCatalogue[i].category,
    		"categoryId" : questionCatalogue[i].categoryId
        });
    }

    var myString = JSON.stringify(jsonQuestions);
    return myString;

}

function createQuestions(){
	
	var langDe = "de";
	var langEn = "en";
	var categoryVisionDe = "Vision";
	var categoryVisionId = 1;
	var categoryInnovationDe = "Unterstützung von Innvovation";
	var categoryInnovationId = 2;
	var categoryTaskorientationDe = "Aufgabenorientierung";
	var categoryTaskorientationId = 3;
	var categorySecurityDe = "Partizipative Sicherheit";
	var categorySecurityId = 4;
	var categoryStructureDe = "Struktur";
	var categoryStructureId = 5;
	var categoryVirtualCompetenceDe = "Virtuelle Kompetenz";
	var categoryVirtualCompetenceId = 6;
	
	var questions = new Array();
	
	questions[0] = new Array();
	questions[0]["language"] = langDe;
	questions[0]["description"] = "In diesem Team ist allen klar, was wir erreichen wollen.";
	questions[0]["category"] = categoryVisionDe;
	questions[0]["categoryId"] = categoryVisionId;

	questions[1] = new Array();
	questions[1]["language"] = langDe;
	questions[1]["description"] = "Wir wissen, dass wir uns aufeinander verlassen können.";
	questions[1]["category"] = categoryInnovationDe;
	questions[1]["categoryId"] = categoryInnovationId;

	questions[2] = new Array();
	questions[2]["language"] = langDe;
	questions[2]["description"] = "Wir haben anregende Diskussionen darüber, wie wir am besten arbeiten.";
	questions[2]["category"] = categoryTaskorientationDe;
	questions[2]["categoryId"] = categoryTaskorientationId;

	questions[3] = new Array();
	questions[3]["language"] = langDe;
	questions[3]["description"] = "Wir treffen uns ausreichend häufig, um effektiv zu kommunizieren und zu koordinieren.";
	questions[3]["category"] = categorySecurityDe;
	questions[3]["categoryId"] = categorySecurityId;

	questions[4] = new Array();
	questions[4]["language"] = langDe;
	questions[4]["description"] = "Die Teammitglieder bieten einander immer schnell Hilfe an, um etwas Neues auszuprobieren.";
	questions[4]["category"] = categoryInnovationDe;
	questions[4]["categoryId"] = categoryInnovationId;

	questions[5] = new Array();
	questions[5]["language"] = langDe;
	questions[5]["description"] = "Wir haben alle Einfluss auf endgültige Entscheidungen im Team.";
	questions[5]["category"] = categorySecurityDe;
	questions[5]["categoryId"] = categorySecurityId;

	questions[6] = new Array();
	questions[6]["language"] = langDe;
	questions[6]["description"] = "Wir halten uns über arbeitsrelevante Dinge gegenseitig auf dem Laufenden.";
	questions[6]["category"] = categorySecurityDe;
	questions[6]["categoryId"] = categorySecurityId;

	questions[7] = new Array();
	questions[7]["language"] = langDe;
	questions[7]["description"] = "In unserem Team herrscht ein Gefühl von Sicherheit und Vertrauen.";
	questions[7]["category"] = categorySecurityDe;
	questions[7]["categoryId"] = categorySecurityId;

	questions[8] = new Array();
	questions[8]["language"] = langDe;
	questions[8]["description"] = "Wir sind jederzeit aufgeschlossen gegenüber neuen Ideen.";
	questions[8]["category"] = categoryInnovationDe;
	questions[8]["categoryId"] = categoryInnovationId;

	questions[9] = new Array();
	questions[9]["language"] = langDe;
	questions[9]["description"] = "Alle Teammitglieder fühlen sich den Zielen des Teams verpflichtet.";
	questions[9]["category"] = categoryVisionDe;
	questions[9]["categoryId"] = categoryVisionId;

	questions[10] = new Array();
	questions[10]["language"] = langDe;
	questions[10]["description"] = "Wir können offen über Fehler sprechen.";
	questions[10]["category"] = categoryTaskorientationDe;
	questions[10]["categoryId"] = categoryTaskorientationId;

	questions[11] = new Array();
	questions[11]["language"] = langDe;
	questions[11]["description"] = "Wir stimmen mit unsere Ziele überein.";
	questions[11]["category"] = categoryVisionDe;
	questions[11]["categoryId"] = categoryVisionId;

	questions[12] = new Array();
	questions[12]["language"] = langDe;
	questions[12]["description"] = "Es herrscht bei uns eine Atmosphäre, in der konstruktive Kritik geübt wird.";
	questions[12]["category"] = categoryTaskorientationDe;
	questions[12]["categoryId"] = categoryTaskorientationId;

	questions[13] = new Array();
	questions[13]["language"] = langDe;
	questions[13]["description"] = "Wir unterstützen einander in Ideen über neue und verbesserte Arbeitsprozesse.";
	questions[13]["category"] = categoryInnovationDe;
	questions[13]["categoryId"] = categoryInnovationId;

	questions[14] = new Array();
	questions[14]["language"] = langDe;
	questions[14]["description"] = "Wir unterstützen uns gegenseitig bei der Erledigung unserer Aufgabe.";
	questions[14]["category"] = categoryTaskorientationDe;
	questions[14]["categoryId"] = categoryTaskorientationId;

	questions[15] = new Array();
	questions[15]["language"] = langDe;
	questions[15]["description"] = "Jeder im Team trägt zur Entscheidungsfindung bei.";
	questions[15]["category"] = categorySecurityDe;
	questions[15]["categoryId"] = categorySecurityId;

	questions[16] = new Array();
	questions[16]["language"] = langDe;
	questions[16]["description"] = "Unsere Teamregeln beinhalten auch Erwartungen zum Kommunikations- und Rückmeldeverhalten. (Vereinbarungen)";
	questions[16]["category"] = categoryStructureDe;
	questions[16]["categoryId"] = categoryStructureId;

	questions[17] = new Array();
	questions[17]["language"] = langDe;
	questions[17]["description"] = "Wir haben durchgängig die richtigen Werkzeuge für effektive Zusammenarbeit verfügbar. (Infrastruktur)";
	questions[17]["category"] = categoryStructureDe;
	questions[17]["categoryId"] = categoryStructureId;

	questions[18] = new Array();
	questions[18]["language"] = langDe;
	questions[18]["description"] = "Alle im Team haben ausreichende Kenntnisse für die situativ angemessene Anwendung der bereitgestellten Kooperationswerkzeuge. (Medienkompetenz)";
	questions[18]["category"] = categoryVirtualCompetenceDe;
	questions[18]["categoryId"] = categoryVirtualCompetenceId;

	questions[19] = new Array();
	questions[19]["language"] = langDe;
	questions[19]["description"] = "Unsere Teambesprechungen finden regelmäßig statt und werden sorgfältig vorbereitet. Inhalte, Ziele und Ablauf sind klar.  (Regeln)";
	questions[19]["category"] = categoryStructureDe;
	questions[19]["categoryId"] = categoryStructureId;

	questions[20] = new Array();
	questions[20]["language"] = langDe;
	questions[20]["description"] = "Im Team werden die vorhandenen Kommunikationskanäle gemäß der getroffenen Absprachen  effizient eingesetzt.  (Kommunikation)";
	questions[20]["category"] = categoryVirtualCompetenceDe;
	questions[20]["categoryId"] = categoryVirtualCompetenceId;

	questions[21] = new Array();
	questions[21]["language"] = langDe;
	questions[21]["description"] = "In unserem Team besteht eine angemessene Balance zwischen Autonomie / Flexibilität und Zusammenhalt. (Selbstorganisation)";
	questions[21]["category"] = categoryVirtualCompetenceDe;
	questions[21]["categoryId"] = categoryVirtualCompetenceId;

	questions[22] = new Array();
	questions[22]["language"] = langDe;
	questions[22]["description"] = "Wir haben eine klare Verteilung von Rollen, Aufgaben und Verantwortlichkeiten im Team. (Klarheit)";
	questions[22]["category"] = categoryStructureDe;
	questions[22]["categoryId"] = categoryStructureId;

	questions[23] = new Array();
	questions[23]["language"] = langDe;
	questions[23]["description"] = "Es gibt auch regelmäßig informelle Kontakte zwischen allen Teammitgliedern. (Vertrauen)";
	questions[23]["category"] = categoryVirtualCompetenceDe;
	questions[23]["categoryId"] = categoryVirtualCompetenceId;

	return questions;
}

function questionsCreatedSuccess(questions){
	alert("Questions successfully created");
}

/******* Deletion of Questions ******************************/
function deleteAllQuestions(){
	var biscatoQuestionURL = getBiscatoQuestionaireURL();
	$.ajax(
            {
                type: "DELETE",
                url: biscatoQuestionURL,
                data: "{}",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    questionsDeletedSuccess(data);
                },
                error: function (msg, url, line) {
                    alert('error trapped in error: function(msg, url, line)');
                    alert('msg = ' + msg + ', url = ' + url + ', line = ' + line);

                }
            });	
}

function questionsDeletedSuccess(data){
	alert("All questions have been deleted successfully");
}
/******* Creation of Test Answers ******************************/
function createTestAnswers(){
	var biscatoQuestionaireURL = getBiscatoQuestionaireURL();
	
    $.ajax(
        {
            type: "GET",
            url: biscatoQuestionaireURL,
            data: "{}",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
            	handleCreateTestAnswers(data.question);
            },
            error: function (msg, url, line) {
                alert('error trapped in error: function(msg, url, line)');
                alert('msg = ' + msg + ', url = ' + url + ', line = ' + line);

            }
        });
}

function handleCreateTestAnswers(questions){

	var users = getTestAnswerUsers();
//	var numberOfVotings = getTestNumberOfVotings(); TODO: implement test data for past votes (4 over a year or so) !Serverside!

	for(var usr in users){
		
		var answers = new Array();
		
		for(var question in questions){
			
	        answers[question] = new Array();
	        answers[question]["questionId"] = questions[question].id;
	        answers[question]["questionText"] = questions[question].description;
	        answers[question]["questionCategory"] = questions[question].category;
	        answers[question]["questionCategoryId"] = questions[question].categoryId;
	        answers[question]["user"] = users[usr];
	        answers[question]["rating"] = getRandomIntBetweenZeroAndTen();
		}
		
		var votes = convertVoteToJSONFormat(answers);
		
		var biscatoAnswerURL = getBiscatoAnswerURL();
		$.ajax(
	            {
	                type: "POST",
	                url: biscatoAnswerURL,
	                data: votes,
	                contentType: "application/json; charset=utf-8",
	                dataType: "json",
	                success: function (data) {
	                    testAnswersCreatedSuccess(data.question);
	                },
	                error: function (msg, url, line) {
	                    alert('error trapped in error: function(msg, url, line)');
	                    alert('msg = ' + msg + ', url = ' + url + ', line = ' + line);

	                }
	            });
		
	}	
}

function getTestNumberOfVotings(){
	return 4;
}

function getTestAnswerUsers(){
	var users = new Array();
	users[0] = "Hans.Mueller@gmail.com";
	users[1] = "Georg.Friedrich@gmail.com";
	users[2] = "Marcel.Breitling@gmail.com";
	users[3] = "Uwe.Kunz@gmail.com";
	users[4] = "Trevor.Hoffmann@gmail.com";
	users[5] = "Wilhelm.Schmitt@gmail.com";
	users[6] = "Kevin.Krauth@gmail.com";
	users[7] = "Martin.Baumann@gmail.com";
	users[8] = "Matt.Cain@gmail.com";
	return users;
}

function testAnswersCreatedSuccess(data){
	console.log("Test Answers created successfully");
}

function getRandomIntBetweenZeroAndTen () {
    return Math.floor(Math.random() * (10 - 0 + 1)) + 0;
}

/******* Deletion of All Answers ******************************/
function deleteAllAnswers(){
	var biscatoAnswerURL = getBiscatoAnswerURL();
	$.ajax(
            {
                type: "DELETE",
                url: biscatoAnswerURL,
                data: "{}",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    answersDeletedSuccess(data);
                },
                error: function (msg, url, line) {
                    alert('error trapped in error: function(msg, url, line)');
                    alert('msg = ' + msg + ', url = ' + url + ', line = ' + line);

                }
            });
}
function answersDeletedSuccess(data){
	alert("All votes have been deleted successfully");
}

/******* Creation of All Answers on Server ******************************/

function createTestAnswersServer(){
    var biscatoAnswerURL = getBiscatoTestAnswerURL();

    $.ajax(
        {
            type: "POST",
            url: biscatoAnswerURL,
            data: "{}",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                handleCreateServerTestAnswers(data.question);
            },
            error: function (msg, url, line) {
                alert('error trapped in error: function(msg, url, line)');
                alert('msg = ' + msg + ', url = ' + url + ', line = ' + line);

            }
        });
}

function handleCreateServerTestAnswers(data){
    alert("Testanswers successfully created by the server")
}


/******* Creation of One Team with Users ******************************/

function createTeamWithUsers(){
    var biscatoTestTeamAndUserURL = getBiscatoTestTeamAndUserURL();

    $.ajax(
        {
            type: "POST",
            url: biscatoTestTeamAndUserURL,
            data: "{}",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                handleCreateTeamAndUser(data);
            },
            error: function (msg, url, line) {
                alert('error trapped in error: function(msg, url, line)');
                alert('msg = ' + msg + ', url = ' + url + ', line = ' + line);

            }
        });
}

function handleCreateTeamAndUser(data){
    alert("Testanswers successfully created by the server")
}