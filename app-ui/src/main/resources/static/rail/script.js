$(document).ready(function() {
	var stations;
	var REGION = "hyd-mmts";
	var CONTEXT = "http://apps-pratiks.rhcloud.com/rest/rail/";

    var from = $(".typeahead");
    $.get(CONTEXT+"stations?region=hyd-mmts", function(data){
      stations = data;
	  $(".typeahead").typeahead({ source:stations });
	},'json');

	$(".typeahead").change(function() {
	  var current = $(".typeahead").typeahead("getActive");
	  if (current) {
	    // Some item from your model is active!
	    if (current.name == $(".typeahead").val()) {
	      // This means the exact match is found. Use toLowerCase() if you want case insensitive match.
	    } else {
	      // This means it is only a partial match, you can either add a new item
	      // or take the active if you don't want new items
	    }
	  } else {
	    // Nothing is active so it is a new value (or maybe empty value)
	  }
	});

	$("#btnSearch").click(function() {
		var from, to;
		stations.forEach(function(station) {
			if ($("#from").val() == station.name) {
				from = station.code;
			} else if ($("#to").val() == station.name) {
				to = station.code;
			}
		});

		if (from && to) {
			var url;
			if ($("#nextHourCount").is(':checked')) {
				url = CONTEXT+"findTrains?"+$.param({"region": REGION, "from": from, "to": to, "nextHourCount": 4});	
			} else {
				url = CONTEXT+"findTrains?"+$.param({"region": REGION, "from": from, "to": to});	
			}
			
			$("#timetable > table > tbody > tr").remove();
			$.get(url, function(trains) {
				document.querySelector('.mdl-layout').MaterialLayout.drawerToggleHandler_();
				$("#timetable").show();
				if (trains.length == 0) {
					var text = "No Trains";
					if ($("#nextHourCount").is(':checked')) {
						text = text + " in next 4 hours";
					}
					$("#timetable > table > tbody").append("<tr><td colspan='4' style='text-align: center'>"+text+"</td></tr>");
					return;
				}


				trains.forEach(function(train) {
					$("#timetable > table > tbody").append(
						"<tr><td class='mdl-cell--2 no-padding' style='padding-left: 0px !'>"+train.id+"</td><td>"+train.name+"</td>"
						+"<td>"+train.routes[0].arrival+"</td>"
						+"<td class=''>"+train.routes[1].arrival+"</td>"
						+"<td class='mdl-cell--hide-phone'>"+train.trainType+"</td>"
						+"</tr>");
				});
			});
		}

	});
});