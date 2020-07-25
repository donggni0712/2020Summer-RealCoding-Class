import React, { Component } from "react";
import { Route } from "react-router-dom";
import CallApi from "./CallApi";

// Stateless component
const Weather = (props) => {
  const { cityName } = props.match.params;

  console.log(cityName);
  return (
    <div>
      <h1>Weather : {cityName}</h1>
      <CallApi />
      <p>Here is front page.!</p>
    </div>
  );
};

export default Weather;
