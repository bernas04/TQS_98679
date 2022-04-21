$(document).ready(function () {
    loadWorldData();
    loadCountrys();
});




function loadCountrys(){
    $.get("http://localhost:8080/api/regions", function(data, status){
        for (var i=0; i< data.length; i++){
            $("#selectCountry").append(new Option(data[i].name, data[i].iso))
        }
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
        confimed_diff = data.confirmed_diff;
        deaths_diff = data.deaths_diff;
        recovered_diff = data.recovered_diff;
        active_diff = data.active_diff;
        fatality_rate = data.fatality_rate;
        document.getElementById("totalCases").innerHTML = confirmed ;
        document.getElementById("totalDeaths").innerHTML = deaths;
        document.getElementById("newCases").innerHTML = confimed_diff;
        document.getElementById("newDeaths").innerHTML = deaths_diff;
        document.getElementById("recovered_diff").innerHTML = recovered_diff;



        document.getElementById("fatality_rate").innerHTML = fatality_rate.toFixed(4);
    });

    $.get("http://localhost:8080/api/percentages", function(data, status){
        if (data[0]>0){
            $('#confirmedPercentage').text(data[0]+"%");
            $('#confirmedPercentage').addClass('text-success small pt-1 fw-bold');
            $('#incDecConf').text("increase since the last update");

        }   
        else{
            $('#confirmedPercentage').text(-data[0]+"%");
            $('#confirmedPercentage').addClass('text-danger small pt-1 fw-bold');
            $('#incDecConf').text("decrease since the last update");
        } 
        if (data[1]>0){
            $('#deathsPercentage').text(data[1]+"%");
            $('#deathsPercentage').addClass('text-success small pt-1 fw-bold');
            $('#incDecDeaths').text("increase since the last update");
        }   
        else{
            $('#deathsPercentage').text(-data[1]+"%");
            $('#deathsPercentage').addClass('text-danger small pt-1 fw-bold');
            $('#incDecDeaths').text("decrease since the last update");
        } 
        if (data[2]>0){
            $('#fatPercentagem').text(data[2]+"%");
            $('#fatPercentagem').addClass('text-success small pt-1 fw-bold');
            $('#incDecFat').text("increase since the last update");
        }   
        else{
            $('#fatPercentagem').text(-data[2]+"%");
            $('#fatPercentagem').addClass('text-danger small pt-1 fw-bold');
            $('#incDecFat').text("decrease since the last update");
        } 
    });

}