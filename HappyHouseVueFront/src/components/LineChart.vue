<template>
    <div>
        <br>
        <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;">
            <h1 class="mb-3">[{{aptName}}] 거래가 그래프 </h1>
            <p>Eirmod sed ipsum dolor sit rebum labore magna erat. Tempor ut dolore lorem kasd vero ipsum sit eirmod
                sit.
                Ipsum diam justo sed rebum vero dolor duo.</p>
        </div>
        <div class="wow fadeInUp" data-wow-delay="0.1s">
            <canvas ref="barChart"></canvas>
        </div>
    </div>
</template>
<script>
import http from "@/common/axios.js";

import { Chart, registerables } from "chart.js";
Chart.register(...registerables);

export default {
    props: ["dealInfo"],
    data: () => ({
        type: "bar",
        data: {
            labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange", "test"],
            datasets: [
                {
                    label: "# of Votes",
                    data: [12, 19, 3, 5, 2, 3, 10],
                    backgroundColor: [
                        "rgba(255, 99, 132, 0.2)",
                    ],
                    borderColor: [
                        "rgba(255, 99, 132, 1)",
                    ],
                    borderWidth: 1,
                },
            ],
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                },
            },
        },
    }),
    mounted() {
        this.setData();
    },
    methods: {
        createChart() {
            console.log(this.data);
            new Chart(this.$refs.barChart, {
                type: "bar",
                data: this.data,
                options: this.options,
            });
        },
        async setData() {
            this.data.datasets[0].label = this.aptName + "의 거래가 정보";
            let response = await http.get("/RecentApt/" + this.aptName);
            let { data } = response;
            this.data.labels = [];
            for (let i = 0; i < data.length; i++) {
                let dayInfo = data[i].dealYear + "." + data[i].dealMonth;
                this.data.labels.push(dayInfo);
            }
            console.log(this.data.datasets[0].data);
            this.data.datasets[0].data = [];
            for (let i = 0; i < data.length; i++) {
                var amount = data[i].dealAmount;
                amount = amount.trim();
                amount = amount.replace(",", "");
                this.data.datasets[0].data.push(amount);
            }
            this.createChart();
        },
    },
    computed: {
        aptName: {
            get() {
                return this.$store.getters.getApartName;
            },
            set(aptName) {
                this.$store.commit("SET_APARTMENT_NAME", aptName);
            },
        },
    },
};
</script>
<style scoped>
</style>
