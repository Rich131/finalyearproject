/** This JavaScript file will control the drawing of elements within the dashboard
 * 	as well as updating the display when parameters are changed
 */



var barData = {
		labels: ["AHT", "ACW", "FCR", "Call Quality", "Customer Review", "# Calls"],
		datasets: [
		{
            label: "My First dataset",
            fillColor: "rgba(220,220,220,0.5)",
            strokeColor: "rgba(220,220,220,0.8)",
            highlightFill: "rgba(220,220,220,0.75)",
            highlightStroke: "rgba(220,220,220,1)",
            data: [65, 59, 90, 81, 56, 55]
        },
        {
            label: "My Second dataset",
            fillColor: "rgba(151,187,205,0.5)",
            strokeColor: "rgba(151,187,205,0.8)",
            highlightFill: "rgba(151,187,205,0.75)",
            highlightStroke: "rgba(151,187,205,1)",
            data: [28, 48, 40, 19, 96, 27]
        }
    ]
};

// getting targets from dashboard
var ahtTarg = document.getElementById("ahtTarg").value;
var acwTarg = document.getElementById("acwTarg").value;
var fcrTarg = document.getElementById("fcrTarg").value;
var callQTarg = document.getElementById("callQTarg").value;
var custSatTarg = document.getElementById("custSatTarg").value;
var numCallsTarg = document.getElementById("numCallsTarg").value;


var countVG = document.getElementById("countVG").innerText
var countG = document.getElementById("countG").innerText
var countAvg = document.getElementById("countAvg").innerText
var countB = document.getElementById("countB").innerText
var countVB = document.getElementById("countVB").innerText

// getting values from table
var ahtVal = document.getElementById("aht").innerText;
var acwVal = document.getElementById("acw").innerText;
var fcrVal = document.getElementById("fcr").innerText;
var callQVal = document.getElementById("callQ").innerText;
var custSatVal = document.getElementById("custSat").innerText;
var numCallsVal = document.getElementById("numCalls").innerText;

var ahtBarData = {
		labels: ["AHT"],
		datasets: [
		{
			label: "Average Handle Time",
			fillColor: "rgba(220,220,220,0.5)",
            strokeColor: "rgba(220,220,220,0.8)",
            highlightFill: "rgba(220,220,220,0.75)",
            highlightStroke: "rgba(220,220,220,1)",
            data: [ahtVal]
		},
        {
            label: "Target",
            fillColor: "rgba(151,187,205,0.5)",
            strokeColor: "rgba(151,187,205,0.8)",
            highlightFill: "rgba(151,187,205,0.75)",
            highlightStroke: "rgba(151,187,205,1)",
            data: [ahtTarg]
        }
	]
}

var acwBarData = {
		labels: ["ACW"],
		datasets: [
		{
			label: "Average Handle Time",
			fillColor: "rgba(220,220,220,0.5)",
            strokeColor: "rgba(220,220,220,0.8)",
            highlightFill: "rgba(220,220,220,0.75)",
            highlightStroke: "rgba(220,220,220,1)",
            data: [acwVal]
		},
        {
            label: "Target",
            fillColor: "rgba(151,187,205,0.5)",
            strokeColor: "rgba(151,187,205,0.8)",
            highlightFill: "rgba(151,187,205,0.75)",
            highlightStroke: "rgba(151,187,205,1)",
            data: [acwTarg]
        }
	]
}

var fcrBarData = {
		labels: ["FCR"],
		datasets: [
		{
			label: "Average Handle Time",
			fillColor: "rgba(220,220,220,0.5)",
            strokeColor: "rgba(220,220,220,0.8)",
            highlightFill: "rgba(220,220,220,0.75)",
            highlightStroke: "rgba(220,220,220,1)",
            data: [fcrVal]
		},
        {
            label: "Target",
            fillColor: "rgba(151,187,205,0.5)",
            strokeColor: "rgba(151,187,205,0.8)",
            highlightFill: "rgba(151,187,205,0.75)",
            highlightStroke: "rgba(151,187,205,1)",
            data: [fcrTarg]
        }
	]
}

var callQBarData = {
		labels: ["QUAL"],
		datasets: [
		{
			label: "Average Handle Time",
			fillColor: "rgba(220,220,220,0.5)",
            strokeColor: "rgba(220,220,220,0.8)",
            highlightFill: "rgba(220,220,220,0.75)",
            highlightStroke: "rgba(220,220,220,1)",
            data: [callQVal]
		},
        {
            label: "Target",
            fillColor: "rgba(151,187,205,0.5)",
            strokeColor: "rgba(151,187,205,0.8)",
            highlightFill: "rgba(151,187,205,0.75)",
            highlightStroke: "rgba(151,187,205,1)",
            data: [callQTarg]
        }
	]
}

var custSatBarData = {
		labels: ["CSAT"],
		datasets: [
		{
			label: "Average Handle Time",
			fillColor: "rgba(220,220,220,0.5)",
            strokeColor: "rgba(220,220,220,0.8)",
            highlightFill: "rgba(220,220,220,0.75)",
            highlightStroke: "rgba(220,220,220,1)",
            data: [custSatVal]
		},
        {
            label: "Target",
            fillColor: "rgba(151,187,205,0.5)",
            strokeColor: "rgba(151,187,205,0.8)",
            highlightFill: "rgba(151,187,205,0.75)",
            highlightStroke: "rgba(151,187,205,1)",
            data: [custSatTarg]
        }
	]
}

var numCallsBarData = {
		labels: ["CALLS"],
		datasets: [
		{
			label: "Average Handle Time",
			fillColor: "rgba(220,220,220,0.5)",
            strokeColor: "rgba(220,220,220,0.8)",
            highlightFill: "rgba(220,220,220,0.75)",
            highlightStroke: "rgba(220,220,220,1)",
            data: [numCallsVal]
		},
        {
            label: "Target",
            fillColor: "rgba(151,187,205,0.5)",
            strokeColor: "rgba(151,187,205,0.8)",
            highlightFill: "rgba(151,187,205,0.75)",
            highlightStroke: "rgba(151,187,205,1)",
            data: [numCallsTarg]
        }
	]
}

var pieData = [
               {
                   value: [countVG],
                   color: "#37FF00",
                   highlight: "#62FF37",
                   label: "Very Good"
               },
               {
                   value: [countG],
                   color: "#66FF00",
                   highlight: "#8AFF3C",
                   label: "Good"
               },
               {
            	   value: [countAvg],
            	   color: "#FFFF00",
            	   highlight: "#FFFF3F",
            	   label: "Average"
               },
               {
                   value: [countB],
                   color: "#FF9100",
                   highlight: "#FFA834",
                   label: "Bad"
               },
               {
                   value: [countVB],
                   color: "#FF2200",
                   highlight: "#FF4528",
                   label: "Very Bad"
               }
           ]

var ahtBar = document.getElementById("ahtBarChart").getContext("2d");
var ahtChart = new Chart(ahtBar).Bar(ahtBarData);

var acwBar = document.getElementById("acwBarChart").getContext("2d");
var acwChart = new Chart(acwBar).Bar(acwBarData);

var fcrBar = document.getElementById("fcrBarChart").getContext("2d");
var fcrChart = new Chart(fcrBar).Bar(fcrBarData);

var callQBar = document.getElementById("callQBarChart").getContext("2d");
var callQChart = new Chart(callQBar).Bar(callQBarData);

var custSatBar = document.getElementById("custSatBarChart").getContext("2d");
var custSatChart = new Chart(custSatBar).Bar(custSatBarData);

var numCallsBar = document.getElementById("numCallsBarChart").getContext("2d");
var numCallsChart = new Chart(numCallsBar).Bar(numCallsBarData);

var ctxPie = document.getElementById("employeePieChart").getContext("2d");
var dashboardChart = new Chart(ctxPie).Pie(pieData);



