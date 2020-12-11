$(document).ready(function() {

    $.getJSON("/api/morning-monitoring/" + user, function (json) {

        var config = {
            type: 'line',
            data: {
                labels: json.map(function(item) {
                        return $.format.date(item.dateTime, 'dd/MM/yyyy');
                }),
                datasets: [{
                    label: 'Waking Heart Rate',
                    backgroundColor: '#A44B5E',
                    borderColor: '#A44B5E',
                    data: json.map(function(item) {
                        return item.wakingHeartRate;
                    }),
                    fill: false,
                }, {
                    label: 'Standing Heart Rate',
                    backgroundColor: '#4BA49A',
                    borderColor: '#4BA49A',
                    data: json.map(function(item) {
                        return item.standingHeartRate;
                    }),
                    fill: false,
                }, {
                    label: 'Perceived Shape',
                    backgroundColor: '#EAB42C',
                    borderColor: '#EAB42C',
                    data: json.map(function(item) {
                        return item.perceivedShape;
                    }),
                    fill: false,
                }, {
                    label: 'Perceived Mental State',
                    backgroundColor: '#FC634A',
                    borderColor: '#FC634A',
                    data: json.map(function(item) {
                        return item.perceivedMentalState;
                    }),
                    fill: false,
                }, {
                    label: 'Sleep Quantity',
                    backgroundColor: '#CE4454',
                    borderColor: '#CE4454',
                    data: json.map(function(item) {
                        return item.sleepQuantity;
                    }),
                    fill: false,
                }, {
                    label: 'Sleep Quality',
                    backgroundColor: '#6B70C1',
                    borderColor: '#6B70C1',
                    data: json.map(function(item) {
                        return item.sleepQuality;
                    }),
                    fill: false,
                }]
            },
            options: {
                /** Adapted from https://stackoverflow.com/a/34404201/12809235 [Accessed: 11 December 2020] **/
                elements: {
                    line: {
                        tension: 0
                    }
                },
                responsive: true,
                tooltips: {
                    mode: 'index',
                    intersect: false,
                },
                hover: {
                    mode: 'nearest',
                    intersect: true
                },
                scales: {
                    xAxes: [{
                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: 'Day'
                        }
                    }],
                    yAxes: [{
                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: 'Values'
                        }
                    }]
                }
            }
        };

        var ctx = document.getElementById('chart').getContext('2d');
        window.myLine = new Chart(ctx, config);

    });

});