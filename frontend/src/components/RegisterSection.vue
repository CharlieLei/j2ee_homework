<template>
    <div>
        <h2>注册页面</h2>
        <hr/>
        <div>
            <table>
                <tr>
                    <td><label for="memberId">用户ID:</label></td>
                    <td><input type="text" name="memberId" id="memberId" v-model="memberId"/></td>
                </tr>
                <tr>
                    <td><label for="password">密码:</label></td>
                    <td><input type="password" name="password" id="password" v-model="password"/></td>
                </tr>
                <tr>
                    <td><label for="email">邮箱:</label></td>
                    <td><input type="email" name="email" id="email" v-model="email"/></td>
                <tr>
                    <td colspan="2"><button v-on:click="registerMember">注册</button></td>
                </tr>
            </table>
        </div>
    </div>
</template>

<script>
    export default {
        name: "RegisterSection",
        data() {
            return {
                memberId: "",
                password: "",
                email: "",
            }
        },
        methods: {
            registerMember: function() {
                this.$axios.get("http:localhost:8081/registerMember",
                    {params: {memberId: this.memberId, password: this.password, email: this.email}})
            }
        }
    }
</script>

<style scoped>

</style>