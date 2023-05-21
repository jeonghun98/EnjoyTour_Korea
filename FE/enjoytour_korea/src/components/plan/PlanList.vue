<template>
    <div>
    <router-link :to="{ name: 'planWrite'}">
        planWrite 이동 (후에 view로 변경예정)
    </router-link>
    </div>
</template>

<script>
export default {
    name: 'PlanList',
    components: {},
    data() {
        return {
            message: '',
        };
    },
    created() {},
    methods: {},
};
</script>

<style scoped></style>