<script>
	$(function() {
		$("#intelligence-slider-range").slider({
			range : true,
			min : 0,
			max : 100,
			values : [ 20, 80 ],
			slide : function(event, ui) {
				$("#intelligence").val(ui.values[0] + " - " + ui.values[1]);
			}
		});
		$("#intelligence").val(
				$("#intelligence-slider-range").slider("values", 0) + " - "
						+ $("#intelligence-slider-range").slider("values", 1));
		
		$("#motivation-slider-range").slider({
			range : true,
			min : 0,
			max : 100,
			values : [ 20, 80 ],
			slide : function(event, ui) {
				$("#motivation").val(ui.values[0] + " - " + ui.values[1]);
			}
		});
		$("#motivation").val(
				$("#motivation-slider-range").slider("values", 0) + " - "
						+ $("#motivation-slider-range").slider("values", 1));
		
		$("#empathy-slider-range").slider({
			range : true,
			min : 0,
			max : 100,
			values : [ 20, 80 ],
			slide : function(event, ui) {
				$("#empathy").val(ui.values[0] + " - " + ui.values[1]);
			}
		});
		$("#empathy").val(
				$("#empathy-slider-range").slider("values", 0) + " - "
						+ $("#empathy-slider-range").slider("values", 1));
		
		$("#communication-slider-range").slider({
			range : true,
			min : 0,
			max : 100,
			values : [ 20, 80 ],
			slide : function(event, ui) {
				$("#communication").val(ui.values[0] + " - " + ui.values[1]);
			}
		});
		$("#communication").val(
				$("#communication-slider-range").slider("values", 0) + " - "
						+ $("#communication-slider-range").slider("values", 1));
		
		$("#initiative-slider-range").slider({
			range : true,
			min : 0,
			max : 100,
			values : [ 20, 80 ],
			slide : function(event, ui) {
				$("#initiative").val(ui.values[0] + " - " + ui.values[1]);
			}
		});
		$("#initiative").val(
				$("#initiative-slider-range").slider("values", 0) + " - "
						+ $("#initiative-slider-range").slider("values", 1));
		
		$("#patience-slider-range").slider({
			range : true,
			min : 0,
			max : 100,
			values : [ 20, 80 ],
			slide : function(event, ui) {
				$("#patience").val(ui.values[0] + " - " + ui.values[1]);
			}
		});
		$("#patience").val(
				$("#patience-slider-range").slider("values", 0) + " - "
						+ $("#patience-slider-range").slider("values", 1));
		
		$("#experience-slider-range").slider({
			range : true,
			min : 0,
			max : 100,
			values : [ 20, 80 ],
			slide : function(event, ui) {
				$("#experience").val(ui.values[0] + " - " + ui.values[1]);
			}
		});
		$("#experience").val(
				$("#experience-slider-range").slider("values", 0) + " - "
						+ $("#experience-slider-range").slider("values", 1));
	});
</script>