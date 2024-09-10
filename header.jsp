<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.json.JSONObject"%>
<%@ page import="java.io.InputStreamReader, java.net.URL, java.net.HttpURLConnection"%>
<%
    String apiKey = "your_openweathermap_api_key";
    String city = "Seoul";
    String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

    URL url = new URL(urlString);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.connect();

    InputStreamReader reader = new InputStreamReader(conn.getInputStream(), "UTF-8");
    StringBuilder jsonData = new StringBuilder();
    int data;
    while ((data = reader.read()) != -1) {
        jsonData.append((char) data);
    }
    reader.close();
    JSONObject jsonObject = new JSONObject(jsonData.toString());
    String weather = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
%>
<div style="text-align:right;">
    <p>Weather in <%= city %>: <%= weather %></p>
</div>
