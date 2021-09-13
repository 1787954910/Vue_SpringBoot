import ElementPlus from 'element-plus'
import 'element-plus/lib/theme-chalk/index.css'
import locale from 'element-plus/lib/locale/lang/zh-cn'

// @ts-ignore
export default (app) => {
  app.use(ElementPlus, { locale })
}
