<template>
    Employ ID: <input v-model="employid" placeholder="employid"><br>
    Name: <input v-model="name" placeholder="name"><br>
    Email: <input v-model="email" placeholder="email"><br>
    Seat: <input v-model="seatInfo" disabled><br>
    <button type="button" class="btn btn-success" @click="editImageProduct">Save</button>
    <table>
        <thead>
            <tr>
                <th>座位代號</th>
                <th>樓層</th>
                <th>號碼</th>
                <th>所有人</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="item in  items " :key="item.FLOOR_SEAT_SEQ" :class="isSelected(item)" @click="toggleSelected(item)">
                <td>{{ item.seatInfo }}</td>
                <td>{{ item.floorNo }}</td>
                <td>{{ item.seatNo }}</td>
                <td>{{ item.employeeId }}</td>
            </tr>
        </tbody>
    </table>
</template>


<script>
import axios from 'axios';
export default {
    inject: ['reload'],
    name: 'searInfroFront',
    data() {
        return { items: [], selectedRows: [], notEmptyRows: [], employeeIDs: [] }
    },
    mounted() {
        this.getUserData();
    },
    methods: {

        getUserData() {
            axios.get('http://localhost:8080/getAllInfo').then((response) => {

                console.log(response.data);

                this.items = response.data;
                var temp_items = response.data;

                temp_items.forEach((item) => {
                    if (item.employeeId !== null && item.employeeId.trim() !== "") {
                        this.notEmptyRows.push(item);
                        this.employeeIDs.push(item.employeeId);
                    }
                    //console.log(item.employeeId.length);
                });

                //console.log(this.items);)
            });
        },
        isSelected(item) {
            if (this.notEmptyRows.indexOf(item) !== -1) {
                return "no-empty";
            } else if (this.selectedRows.indexOf(item) !== -1) {
                return "selected"
            }
            return "normal";


        },
        toggleSelected(item) {
            const index = this.selectedRows.indexOf(item);
            const seat_index = this.notEmptyRows.indexOf(item);
            if (index === -1 && seat_index !== -1) {
                alert('該座位無法選擇');
            }
            else if (index === -1) {
                this.seatInfo = item.seatInfo;
                this.selectedRows.push(item);
            } else {
                this.seatInfo = item.seatInfo;
                this.selectedRows.pop();
                this.selectedRows.push(item);
            }
        },

        editImageProduct() {
            ///insertInfo/{id}/{name}/{email}/{seat}
            if (this.employeeIDs.indexOf(this.employid) === -1) {
                axios.get('http://localhost:8080/insertInfo/' + this.employid + '/' + this.name + '/' + this.email + '/' + this.seatInfo).then((response) => {

                    console.log(response.data)
                });
            } else if (this.employid.length == 5) {
                // /updateSeat/{id}/{seat}
                console.log('http://localhost:8080/updateSeat/' + this.employid + '/' + this.seatInfo)
                axios.get('http://localhost:8080/updateSeat/' + this.employid + '/' + this.seatInfo).then((response) => {
                    console.log(response.data)
                    location.reload();
                });
            }


        }
    }
}
</script>

<style scoped>
#app {
    max-width: 800px;
    margin: 0 auto;
    padding: 24px;
    font-family: Arial, sans-serif;
    color: #333;
}

/* Style the paragraph element inside the container */
#app p {
    margin: 16px 0;
    font-size: 18px;
}

table {
    border-collapse: collapse;
    width: 100%;
    max-width: 600px;
    margin: 20px auto;
}

th,
td {
    padding: 10px;
    text-align: left;
    border-bottom: 1px solid #ddd;
    cursor: pointer;
}

th {
    background-color: #f2f2f2;
}

tr:hover {
    background-color: #f5f5f5;
}

.selected {
    background-color: #d2fad5;
}

.normal {
    background-color: #6a6868;
}

.no-empty {
    background-color: #ff0000
}

tbody tr:last-child td {
    border-bottom: none;
}

@media screen and (max-width: 600px) {
    table {
        font-size: 14px;
    }
}

@media screen and (max-width: 400px) {
    table {
        font-size: 12px;
    }
}
</style>