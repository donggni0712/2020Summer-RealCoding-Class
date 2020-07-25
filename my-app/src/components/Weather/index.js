import React, { Component } from "react";
import { Route } from "react-router-dom";
import CallApi from "./CallApi";

// Stateless component
const Weather = (props) => {
  const { cityName } = props.match.params;

  console.log(cityName);
  return (
    <div>
      <h1>도시 이름 : {cityName}</h1>
      <CallApi CityName={cityName} />
      <p>Here is front page.!</p>
    </div>
  );
};

export default Weather;
