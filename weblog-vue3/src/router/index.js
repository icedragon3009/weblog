import Index from "#/frontend/index.vue"
import Login from '#/admin/login.vue'
import { createRouter, createWebHashHistory } from "vue-router"

// 统一声名所有路由
const routes = [
    {
        path: "/",                  // 路由地址
        component: Index,           // 路由组件
        meta: {                     // meta信息
            title: "Weblog 首页"
        }
    },{
        path: "/login",                  // 路由地址
        component: Login,           // 路由组件
        meta: {                     // meta信息
            title: "登录页"
        }
    }
]

// 创建路由
const router = createRouter(
    {
        history: createWebHashHistory(), 
        routes
    }
)

export default router

// 上述代码中，我们先通过 import 关键词导入了 index.vue 组件，
// 以及 vue-router 路由中的相关方法。然后，我们定义一个 routes 数组，用于统一声明所有路由。
// 最后，我们创建了路由，并使用 export default 关键字导出了 router 对象。