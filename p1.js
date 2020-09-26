

function myfunct(){
    const newYears = "1 1 2021"
    var d = new Date();
    var endDate = new Date(newYears);
    
    var remainingsecs=(endDate - d)/1000;
        
var daysPending = Math.floor(remainingsecs/(24*3600));
var hoursPending = Math.floor(remainingsecs/(3600)%24);
var minsPending = Math.floor(remainingsecs/(60)%60);
var secsPending = Math.floor(remainingsecs%60);

document.getElementById('days').innerHTML=daysPending;
document.getElementById('hours').innerHTML=hoursPending;
document.getElementById('mins').innerHTML=minsPending;
document.getElementById('secs').innerHTML=secsPending;

console.log(daysPending,hoursPending,minsPending,secsPending);
}
 
setInterval(myfunct,1000);






 
