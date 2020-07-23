import React from "react";
import { BrowserRouter } from "react-router-dom";
import "./App.css";
//import { sum as add, multiple } from "./Math";
//import Math from "./Math";
import Header from "./components/Header";
import RouterApp from "./RouterApp";

function App() {
  //console.log(Math);
  //const sum = "SUM";
  //console.log(sum);
  //console.log(add);
  //console.log(multiple);

  return (
    <BrowserRouter>
      <RouterApp />
    </BrowserRouter>
  );
}

export default App;
