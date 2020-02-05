<template>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Name</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="user in users" :key="user.id">
                <td class="id">{{user.id}}</td>
                <td class="login">{{user.login}}</td>
                <td class="name">{{user.name}}</td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    export default {
        name: "Users",
        props: ['users']
    }
</script>

<style scoped>

</style>