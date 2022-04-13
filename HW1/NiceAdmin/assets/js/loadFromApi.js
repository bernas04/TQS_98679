$(document).ready(function () {
    load_graphic();
    loadWorldData();

    
});

function load_graphic(){

    $.get("http://localhost:8080/api/world7days", function(data, status){
        var active= [];
        var deaths = [];
        var recovery = [];
        var datas = [];
        
        for (var i=0;i <data.length;i++){
            active.push(data[i].active);
            deaths.push(data[i].deaths);
            recovery.push(data[i].recovered);
            datas.push(data[i].date);
        }

        

        new ApexCharts(document.querySelector("#reportsChart"), {
            series: [{
            name: 'Total Cases',
            data: active
            }, {
            name: 'Total deaths',
            data: deaths
            }, {
            name: 'Recovery',
            data: recovery
            }],
            chart: {
            height: 350,
            type: 'area',
            toolbar: {
                show: false
            },
            },
            markers: {
            size: 4
            },
            colors: ['#4154f1', '#2eca6a', '#ff771d'],
            fill: {
            type: "gradient",
            gradient: {
                shadeIntensity: 1,
                opacityFrom: 0.3,
                opacityTo: 0.4,
                stops: [0, 90, 100]
            }
            },
            dataLabels: {
            enabled: false
            },
            stroke: {
            curve: 'smooth',
            width: 2
            },
            xaxis: {
            type: 'datetime',
            categories: datas
            },
            tooltip: {
            x: {
                format: 'yyyy/MM/dd'
            },
            }
        }).render();
    });

}


function loadWorldData(){
    let date, last_update, confirmed, deaths, recovered, confimed_diff, deaths_diff, recovered_diff, active_diff, fatality_rate;

    $.get("http://localhost:8080/api/world", function(data, status){
        date = data.date;
        last_update = data.last_update;
        confirmed = data.confirmed;
        deaths = data.deaths;
        recovered = data.recovered;
        confimed_diff = data.confimed_diff;
        deaths_diff = data.deaths_diff;
        recovered_diff = data.recovered_diff;
        active_diff = data.active_diff;
        fatality_rate = data.fatality_rate;
        document.getElementById("totalCases").innerHTML = confirmed ;
        document.getElementById("totalDeaths").innerHTML = deaths;
        document.getElementById("fatality_rate").innerHTML = fatality_rate.toFixed(4);
    });

}