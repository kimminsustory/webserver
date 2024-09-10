function getWeather() {
    fetch("http://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=YOUR_API_KEY&units=metric")
        .then(response => response.json())
        .then(data => {
            const weatherDiv = document.getElementById("weather");
            weatherDiv.innerHTML = `현재 날씨: ${data.weather[0].description}, 온도: ${data.main.temp}°C`;
        })
        .catch(error => console.error("Error fetching weather data:", error));
}

window.onload = getWeather;
