import React from "react";

class CallApi extends React.Component {
  state = {
    name: "String",
    main: "String",
    description: "String",
    temp: "Float",
    pressure: "Int",
    humidity: "Int",
    temp_min: "Float",
    temp_max: "Float",
  };

  componentDidMount() {
    const cityWeatherdata = fetch(
      `http://localhost:8080/weather-service/weathers?cityName=Seoul`
    )
      .then((res) => {
        return res.json(); //Promise 반환
      })
      .then((json) => {
        // console.log(json);
        //jsonObj = JSON.parse(json);
        this.setState({
          name: json.name,
          main: json.weather[0].main,
          description: json.weather[0].description,
          temp: json.main.temp,
          pressure: json.main.pressure,
          humidity: json.main.humidity,
          temp_min: json.main.temp_min,
          temp_max: json.main.temp_max,
        });
        // 서버에서 주는 json데이터가 출력 됨
      });
  }
  render() {
    return (
      <div>
        <h3>날씨 : {this.state.main}</h3>
        <h3>날씨 상세 : {this.state.description}</h3>
        <h3>온도 : {this.state.temp}</h3>
        <h3>기압 : {this.state.pressure}</h3>
        <h3>습도 : {this.state.humidity}</h3>
        <h3>최저 온도 : {this.state.temp_min}</h3>
        <h3>최고 온도 : {this.state.temp_max}</h3>
      </div>
    );
  }
}

export default CallApi;
