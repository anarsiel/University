<template>
    <div>
        <Post v-for="post in postOrder" :post="post" :key="post.id"/>
    </div>
</template>

<script>
    import Post from "./Post";
    export default {
        name: "Index",
        props: ['posts'],
        components: {Post},
        computed: {
            postOrder: function () {
                return Object.values(this.posts).sort((a, b) => b.id - a.id);
            }
        }
    }
</script>

<style scoped>

</style>