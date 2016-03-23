<script>
	// jQuery function to handle slider ui as input range
	$(function() {
		$("#intelligence-slider-range").slider(
				{
					range : true,
					min : 0,
					max : 100,
					values : [ 20, 80 ],
					slide : function(event, ui) {
						$("#intelligence-range").val(
								" " + ui.values[0] + " - " + ui.values[1]);
					}
				});
		$("#intelligence-range").val(
				" " + $("#intelligence-slider-range").slider("values", 0)
						+ " - "
						+ $("#intelligence-slider-range").slider("values", 1));

		$("#motivation-slider-range").slider(
				{
					range : true,
					min : 0,
					max : 100,
					values : [ 20, 80 ],
					slide : function(event, ui) {
						$("#motivation-range").val(
								ui.values[0] + " - " + ui.values[1]);
					}
				});
		$("#motivation-range").val(
				$("#motivation-slider-range").slider("values", 0) + " - "
						+ $("#motivation-slider-range").slider("values", 1));

		$("#empathy-slider-range").slider({
			range : true,
			min : 0,
			max : 100,
			values : [ 20, 80 ],
			slide : function(event, ui) {
				$("#empathy-range").val(ui.values[0] + " - " + ui.values[1]);
			}
		});
		$("#empathy-range").val(
				$("#empathy-slider-range").slider("values", 0) + " - "
						+ $("#empathy-slider-range").slider("values", 1));

		$("#communication-slider-range").slider(
				{
					range : true,
					min : 0,
					max : 100,
					values : [ 20, 80 ],
					slide : function(event, ui) {
						$("#communication-range").val(
								ui.values[0] + " - " + ui.values[1]);
					}
				});
		$("#communication-range").val(
				$("#communication-slider-range").slider("values", 0) + " - "
						+ $("#communication-slider-range").slider("values", 1));

		$("#initiative-slider-range").slider(
				{
					range : true,
					min : 0,
					max : 100,
					values : [ 20, 80 ],
					slide : function(event, ui) {
						$("#initiative-range").val(
								ui.values[0] + " - " + ui.values[1]);
					}
				});
		$("#initiative-range").val(
				$("#initiative-slider-range").slider("values", 0) + " - "
						+ $("#initiative-slider-range").slider("values", 1));

		$("#patience-slider-range").slider({
			range : true,
			min : 0,
			max : 100,
			values : [ 20, 80 ],
			slide : function(event, ui) {
				$("#patience-range").val(ui.values[0] + " - " + ui.values[1]);
			}
		});
		$("#patience-range").val(
				$("#patience-slider-range").slider("values", 0) + " - "
						+ $("#patience-slider-range").slider("values", 1));

		$("#experience-slider-range").slider(
				{
					range : true,
					min : 0,
					max : 100,
					values : [ 20, 80 ],
					slide : function(event, ui) {
						$("#experience-range").val(
								ui.values[0] + " - " + ui.values[1]);
					}
				});
		$("#experience-range").val(
				$("#experience-slider-range").slider("values", 0) + " - "
						+ $("#experience-slider-range").slider("values", 1));
		
		
		// setting bar / pie data based on values in hidden table
		/** This JavaScript file will control the drawing of elements within the dashboard
		 * 	as well as updating the display when parameters are changed
		 */

		var barData = {
			labels : [ "AHT", "ACW", "FCR", "Call Quality", "Customer Review",
					"# Calls" ],
			datasets : [ {
				label : "My First dataset",
				fillColor : "rgba(220,220,220,0.5)",
				strokeColor : "rgba(220,220,220,0.8)",
				highlightFill : "rgba(220,220,220,0.75)",
				highlightStroke : "rgba(220,220,220,1)",
				data : [ 65, 59, 90, 81, 56, 55 ]
			}, {
				label : "My Second dataset",
				fillColor : "rgba(151,187,205,0.5)",
				strokeColor : "rgba(151,187,205,0.8)",
				highlightFill : "rgba(151,187,205,0.75)",
				highlightStroke : "rgba(151,187,205,1)",
				data : [ 28, 48, 40, 19, 96, 27 ]
			} ]
		};

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
			labels : [ "AHT" ],
			datasets : [ {
				label : "Average Handle Time",
				fillColor : "rgba(220,220,220,0.5)",
				strokeColor : "rgba(220,220,220,0.8)",
				highlightFill : "rgba(220,220,220,0.75)",
				highlightStroke : "rgba(220,220,220,1)",
				data : [ ahtVal ]
			} ]
		}

		var acwBarData = {
			labels : [ "ACW" ],
			datasets : [ {
				label : "Average Handle Time",
				fillColor : "rgba(220,220,220,0.5)",
				strokeColor : "rgba(220,220,220,0.8)",
				highlightFill : "rgba(220,220,220,0.75)",
				highlightStroke : "rgba(220,220,220,1)",
				data : [ acwVal ]
			} ]
		}

		var fcrBarData = {
			labels : [ "FCR" ],
			datasets : [ {
				label : "Average Handle Time",
				fillColor : "rgba(220,220,220,0.5)",
				strokeColor : "rgba(220,220,220,0.8)",
				highlightFill : "rgba(220,220,220,0.75)",
				highlightStroke : "rgba(220,220,220,1)",
				data : [ fcrVal ]
			}]
		}

		var callQBarData = {
			labels : [ "QUAL" ],
			datasets : [ {
				label : "Average Handle Time",
				fillColor : "rgba(220,220,220,0.5)",
				strokeColor : "rgba(220,220,220,0.8)",
				highlightFill : "rgba(220,220,220,0.75)",
				highlightStroke : "rgba(220,220,220,1)",
				data : [ callQVal ]
			}]
		}

		var custSatBarData = {
			labels : [ "CSAT" ],
			datasets : [ {
				label : "Average Handle Time",
				fillColor : "rgba(220,220,220,0.5)",
				strokeColor : "rgba(220,220,220,0.8)",
				highlightFill : "rgba(220,220,220,0.75)",
				highlightStroke : "rgba(220,220,220,1)",
				data : [ custSatVal ]
			}]
		}

		var numCallsBarData = {
			labels : [ "CALLS" ],
			datasets : [ {
				label : "Average Handle Time",
				fillColor : "rgba(220,220,220,0.5)",
				strokeColor : "rgba(220,220,220,0.8)",
				highlightFill : "rgba(220,220,220,0.75)",
				highlightStroke : "rgba(220,220,220,1)",
				data : [ numCallsVal ]
			}]
		}

		var pieData = [ {
			value : [ countVG ],
			color : "#37FF00",
			highlight : "#62FF37",
			label : "Very Good"
		}, {
			value : [ countG ],
			color : "#66FF00",
			highlight : "#8AFF3C",
			label : "Good"
		}, {
			value : [ countAvg ],
			color : "#FFFF00",
			highlight : "#FFFF3F",
			label : "Average"
		}, {
			value : [ countB ],
			color : "#FF9100",
			highlight : "#FFA834",
			label : "Bad"
		}, {
			value : [ countVB ],
			color : "#FF2200",
			highlight : "#FF4528",
			label : "Very Bad"
		} ]
		
		
		var ahtChart, acwChart, fcrChart, callQChart, custSatChart, numCallsChart, dashboardChart;
		
		$('a[data-toggle="tab"]')
				.on('shown.bs.tab', function(e) {
						
					// alert(this.id);
					
					if ((this.id) == 'overview-control' ) {	
						
						var ahtBar = document.getElementById("ahtBarChart")
								.getContext("2d");
						var ahtChart = new Chart(ahtBar).Bar(ahtBarData);

						var acwBar = document.getElementById("acwBarChart")
								.getContext("2d");
						var acwChart = new Chart(acwBar).Bar(acwBarData);

						var fcrBar = document.getElementById("fcrBarChart")
								.getContext("2d");
						var fcrChart = new Chart(fcrBar).Bar(fcrBarData);

						var callQBar = document.getElementById("callQBarChart")
								.getContext("2d");
						var callQChart = new Chart(callQBar).Bar(callQBarData);

						var custSatBar = document.getElementById(
								"custSatBarChart").getContext("2d");
						var custSatChart = new Chart(custSatBar)
								.Bar(custSatBarData);

						var numCallsBar = document.getElementById(
								"numCallsBarChart").getContext("2d");
						var numCallsChart = new Chart(numCallsBar)
								.Bar(numCallsBarData);

						var ctxPie = document
								.getElementById("employeePieChart").getContext(
										"2d");
						var dashboardChart = new Chart(ctxPie).Pie(pieData);
					} else {
						ahtChart.destroy();
					}

				});
		
		
		// testing new loading code

		
	});

	
</script>