console.log("We got here!");

document.getElementsByTagName("p")[0]

function incTime(delta) {
	let elem = document.getElementById("time");
	elem.innerHTML = parseInt(elem.innerText)+delta;
}

setInterval(function() {incTime(5);}, 1000);

function printMessage() {
	console.log("Thank you for clicking.");
}

let canvas = document.getElementById("canvas");
let gc = canvas.getContext('2d');
console.log(gc.fillStyle);
let grad = gc.createLinearGradient(0,0,150,100);
grad.addColorStop(0.0, "red");
grad.addColorStop(1.0, "green");
gc.fillStyle = grad;
gc.fillRect(100, 50, 200, 150);
