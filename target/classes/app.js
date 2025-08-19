function sendGet() {
  const nombre = document.getElementById("nombreGet").value;
  fetch("/hello?nombre=" + encodeURIComponent(nombre))
    .then(r => r.text())
    .then(t => document.getElementById("respGet").innerText = t);
}

function sendPost() {
  const nombre = document.getElementById("nombrePost").value;
  fetch("/hellopost?nombre=" + encodeURIComponent(nombre), { method: "POST" })
    .then(r => r.text())
    .then(t => document.getElementById("respPost").innerText = t);
}

function getRandom() {
  fetch("/api/random")
    .then(r => r.json())
    .then(data => {
      document.getElementById("respRandom").innerText = "Número: " + data.randomNumber;
    });
}
