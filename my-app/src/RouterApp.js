import React from "react";
import { Route } from "react-router-dom";
import Header from "./components/Header";

//import Navigation from "./components/Navigation";
import Home from "./components/Home";
import About from "./components/About";

const RouterApp = () => {
  const cityName = "Seoul";

  return (
    <div className="App">
      <Header cityName={cityName} />
      <p>Hello world!</p>
      {/*<Navigation />*/}
      <Route exact path="/" component={Home} />
      <Route path="/about" component={About} />
    </div>
  );
};

export default RouterApp;
