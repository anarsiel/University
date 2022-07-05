<template>
    <div>
        <Post v-for="post in posts" :post="post" :users="users" :comments="comments" :is-full=true :key="post.id"/>
    </div>
</template>

<script>
    import Post from "./Post";
    export default {
        name: "Index",
        props: ['posts', 'users', 'comments'],
        components: {Post}
    }
</script>

<style scoped>

</style>
