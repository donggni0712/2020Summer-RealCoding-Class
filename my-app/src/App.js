import React from "react";
import "./App.css";
//import { sum as add, multiple } from "./Math";
//import Math from "./Math";
import Header from "./components/Header";

function App() {
  //console.log(Math);
  //const sum = "SUM";
  //console.log(sum);
  //console.log(add);
  //console.log(multiple);

  const cityName = "Seoul";

  return (
    <div className="App">
      <Header cityName={cityName} />
      <p>Hello world!</p>
    </div>
  );
}

export default App;
