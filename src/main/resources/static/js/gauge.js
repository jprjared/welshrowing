$(document).ready(function() {

    $.getJSON("/api/morning-monitoring/latest/" + user, function (json) {

        createGauge(
            "ohr-gauge",
            "ohr-text",
            json.osmoticHeartRate,
            10,
            25,
            5,
            10,
            0,
            5
        );

        createGauge(
            "whr-gauge",
            "whr-text",
            json.wakingHeartRate,
            51,
            60,
            47,
            51,
            30,
            47
        );

        createGauge(
            "shape-gauge",
            "shape-text",
            json.perceivedShape,
            0,
            20,
            20,
            30,
            30,
            40
        );

    });

});

function createGauge(
    canvasId,
    textId,
    actualValue,
    redStart,
    redEnd,
    yellowStart,
    yellowEnd,
    greenStart,
    greenEnd
) {

    $("#" + textId).html(actualValue);

    let allValues = [redStart, redEnd, yellowStart, yellowEnd, greenStart, greenEnd];

    // Adapted from https://stackoverflow.com/a/6102340/12809235 [Accessed: 13 December 2020]
    let maxValue = Math.max.apply(Math, allValues);
    let minValue = Math.min.apply(Math, allValues);

    if (actualValue > maxValue) {
        actualValue = maxValue;
    } else if (actualValue < minValue) {
        actualValue = minValue;
    }

    var opts = {
        angle: -0.2, // The span of the gauge arc
        lineWidth: 0.2, // The line thickness
        radiusScale: 1, // Relative radius
        pointer: {
            length: 0.6, // // Relative to gauge radius
            strokeWidth: 0.035, // The thickness
            color: '#000000' // Fill color
        },
        limitMax: false,     // If false, max value increases automatically if value > maxValue
        limitMin: false,     // If true, the min value of the gauge will be fixed
        colorStart: '#6FADCF',   // Colors
        colorStop: '#8FC0DA',    // just experiment with them
        strokeColor: '#E0E0E0',  // to see which ones work best for you
        generateGradient: true,
        highDpiSupport: true,     // High resolution support
        staticZones: [
            {strokeStyle: "#F03E3E", min: redStart, max: redEnd}, // Red
            {strokeStyle: "#FFDD00", min: yellowStart, max: yellowEnd}, // Yellow
            {strokeStyle: "#30B32D", min: greenStart, max: greenEnd} // Green
        ],
        staticLabels: {
            font: "10px sans-serif",  // Specifies font
            labels: allValues,  // Print labels at these values
            color: "#000000",  // Optional: Label text color
            fractionDigits: 0  // Optional: Numerical precision. 0=round off.
        },
    };
    var target = document.getElementById(canvasId); // your canvas element
    var gauge = new Gauge(target).setOptions(opts); // create sexy gauge!
    gauge.maxValue = maxValue; // set max gauge value
    gauge.setMinValue(minValue);  // Prefer setter over gauge.minValue = 0
    gauge.animationSpeed = 50; // set animation speed (32 is default value)
    gauge.set(actualValue); // set actual value
}