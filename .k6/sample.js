// 실행 : K6_OUT=influxdb=http://localhost:8086/k6 k6 run sample.js

import http from "k6/http";
import { check } from "k6";

export let options = {
    duration: "10s", // 10초 동안 실행
    vus: 10, // 동시 사용자는 10명
    ext: {
        influxdb: {
            address: "http://localhost:8086",
            database: "k6",
            username: "k6",
            password: "k6pass",
            tags: { project: "performance-testing" },
        },
    },
};

export default function () {
    let res = http.get("http://localhost:8080/sample?id=1738634049647");
    check(res, { "status was 200": (r) => r.status == 200 });
}
