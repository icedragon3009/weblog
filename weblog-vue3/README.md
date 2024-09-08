# # Weblog 前端工程 weblog-vue3

## 开始

### 工程创建

使用命令生成基础框架
```sh
npm create vue@latest
```
解释一下目录结构以及相关文件的作用：

node_modules : 项目依赖包文件夹，比如通过 npm install 包名 安装的包都会放在这个目录下，因为现在还没有执行 npm install 命令，所以还未生成该文件夹；
public : 公共资源目录，用于存放公共资源，如 favicon.ico 图标等；
index.html : 首页；
package.json : 项目描述以及依赖；
package-lock.json : 版本管理使用的文件；
README.md : 用于项目描述的 markdown 文档；
src : 核心文件目录，源码都放在这里面；

进入 src 文件夹，目录如下：

assets : 静态资源目录，用于存放样式、图片、字体等；
components: 组件文件夹，通用的组件存放目录；
App.vue: 主组件，也是页面的入口文件，所有页面都是在 App.vue 下进行路由切换的；
main.js : 入口 Javascript 文件；


### Vue常用操作

[VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur).

See [Vite Configuration Reference](https://vitejs.dev/config/).

#### 安装依赖包

```sh
npm install
```

### 运行项目

```sh
npm run dev
```

### 打包

```sh
npm run build
```

## 开发工具 Vscode

前往 VSCode 官网：https://code.visualstudio.com/

### Vscode 插件

- Vue - Official： 专用于构建 Vue 的拓展
- Vue 3 Snippets： HTML 代码自动提示和代码补全插件，提升编码效率。
- 别名路径跳转：可以自由配置映射规则，自由配置可缺省后缀名列表
- Auto Rename Tag：当我们修改 HTML/XML 某个节点标签时，能够自动重命名结束标签的命名。
- Auto Close Tag：自动添加 HTML/XML 结束标签，比如当输入 <p> 标签，则自动添加 </p> 结尾标签。
- CSS Navigation：允许你通过点击 HTML 中的类名，直接跳转至对应的样式代码。
- Path Intellisense：通过 . 的方式导入某个文件时，可自动提示文件路径。


## 前端代码

- index.html ： 首页
- main.js ： 主 js 文件，被`index.html`引用
- App.vue : 主组件，被`main.js`引用

## 组件

### Vue Router

Vue Router 是 Vue.js 官方提供路由管理器。它与 Vue.js 核心深度集成，让构建单页面应用（Single Page Applications，SPA）变得轻而易举。

#### 安装 vue-router

```sh
npm install vue-router
```

#### 配置 Router

- 创建router/index.js，存放相关路由代码

```javascript
// 引用页面
import Index from "#/frontend/index.vue"
// 导入router
import { createRouter, createWebHashHistory } from "vue-router"

// 统一声名所有路由
const routes = [
    {
        path: "/",                  // 路由地址
        component: Index,           // 路由组件
        meta: {                     // meta信息
            title: "Weblog 首页"
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
```

- 使用 router-view
<router-view> 是 Vue Router 的一个核心组件，它是一个功能性组件（functional component）。其主要作用是根据当前的路由（URL）动态地渲染对应的组件，相当于是一个“占位符”，它会自动渲染与当前路径匹配的组件。
需要在 app.vue 中添加该组件，用于动态渲染路由对应的组件：
```html
<template>
   <router-view></router-view>
</template>
```

- 将 Router 添加到 Vue 实例
打开 src/main.js 文件，将 router 导入并添加到 Vue app 实例中，应用声明的路由

```javascript
import { createApp } from 'vue'
import App from './App.vue'
// 导入路由
import router from './router'

const app = createApp(App)

// 应用路由
app.use(router)
app.mount('#app')

```

- 别名引用
在项目的根目录中，找到 Vite 的配置文件 vite.config.js，编辑它，添加alias 配置，代码如下：

```javascript
import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      // 定义一个别名 '@'，该别名对应于当前 JavaScript 模块文件所在目录下的 'src' 目录的绝对文件路径。
      '@': fileURLToPath(new URL('./src', import.meta.url))
      '#': fileURLToPath(new URL('./src/pages', import.meta.url))
    }
  }
})
```

定义好了别名以后，我们将之前的 /router/index.js 文件中的路径引用改进一下，如下：
```javascript
import Index from "@/pages/frontend/index.vue"
import Login from '#/admin/login.vue'
```
### Tailwind CSS

#### 安装
```sh
npm install -D tailwindcss postcss autoprefixer
```
此命令会在你的项目中安装三个依赖，它们分别是：

1、tailwindcss：Tailwind CSS 框架本身。

2、postcss：一个用于转换 CSS 的工具。

3、autoprefixer：一个 PostCSS 插件，用于自动添加浏览器供应商前缀到 CSS 规则中，确保跨浏览器的兼容性。

```sh
npx tailwindcss init -p
```
此命令用于生成 tailwind.config.js 和 postcss.config.js 配置文件。

#### 配置
在 tailwind.config.js 文件中添加所有模板文件的路径:
```javascript
/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{vue, js, ts, jsx, tsx}",
    "./node_modules/flowbite/**/*.js"
  ],
  theme: {
    extend: {},
  },
  plugins: [
    require('flowbite/plugin')
  ],
}
```

#### 引用
编辑 main.css 文件，清空里面初始化项目时自动生成的 css 代码，添加如下代码：
```javascript
@tailwind base;
@tailwind components;
@tailwind utilities;
```

#### 参考资料
官网：https://tailwindcss.com/docs/installation

### Flowbite

Flowbite 是一个基于 Tailwind CSS 创建的组件库，旨在帮助开发者快速构建现代、响应式的 Web 应用界面。在本小节中，小哈将的带着大家在 weblog 项目中集成和使用 Flowbite。

#### 安装 Flowbite
执行如下命令安装 Flowbit :
```sh
npm install flowbite
```
在 tailwind.config.js 文件添加 Flowbit 插件：
```javascript
module.exports = {
	省略...
    plugins: [
        require('flowbite/plugin')
    ]

}
```
另外, 还需要在 tailwind.config.js 文件中，添加 js 相关的文件，因为页面交互需要 js：
```javascript
module.exports = {

    content: [
        "./node_modules/flowbite/**/*.js"
    ]

}
```

#### 参考资料
官网：https://flowbite.com/docs/components/accordion/

### Element Plus

Element Plus 是一个专为 Vue 3 设计的 UI 组件库。它提供了一整套丰富且高质量的组件，从基本的按钮和输入框到复杂的日期选择器和数据表格。这些组件不仅样式美观，而且具有广泛的定制选项。

#### 安装
打开命令行，通过 npm 执行如下命令来安装 Element Plus:
```sh
npm install element-plus --save
```
#### 自动导入
Element Plus 有很多组件，而我们实际项目中，并不是每个组件都会被用到。所以，在生产级项目中，比较推荐按需导入组件，而不是在打包的时候，一次性将所以组件都打包进去，导致相关包非常大，页面初次加载很慢的情况发生。

要想实现按需导入组件，你需要安装unplugin-vue-components 和 unplugin-auto-import这两款插件：
```sh
npm install -D unplugin-vue-components unplugin-auto-import
```
然后把下列代码插入到你的 Vite 配置文件 vite.config.js 中:
```javascript
import { defineConfig } from 'vite'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

export default defineConfig({
  // ...
  plugins: [
    // ...
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    }),
  ],
})
```
完成上面工作后，在控制台 执行 npm run dev 重新构建一下项目。

#### 参考资料
官网查看：https://element-plus.org/zh-CN/component/button.html


### Animate.css
Animate.css 是一个跨浏览器的 CSS 动画库，提供了许多预设的、流畅的动画效果。用户只需添加几个 CSS 类名，就可以轻松实现复杂的动画效果，无需编写任何 JavaScript 代码。

#### 安装
打开命令行，执行如下安装命令：
```sh
npm install animate.css --save
```
#### 引入
在 main.js 文件中引入它：
```javascript
import 'animate.css';
```
#### 参考资料
官网：https://animate.style/

## 其他参考

图标和插画： https://www.iconfont.cn/