import {Injectable} from '@angular/core';

/**
 * localStorage 工具类
 */
@Injectable()
export class StorageUtil {
   // localStorage 默认有效期为一个小时
   public static expireTime = 1000 * 60 * 60;

   constructor() {
   }

   /**
    * 封装key value 存值
    * @param key
    * @param value
    */
   set(key: any, value: any): void {
      let curTime = new Date().getTime();
      localStorage.setItem(key, JSON.stringify({data: value, time: curTime}));
   }

   /**
    * 封装key取值
    * @param key
    * @returns {any}
    */
   get(key: any): any {
      let data = localStorage.getItem(key);
      if (!data) {
         return null;
      }
      try {
         let obj = JSON.parse(data);
         if (new Date().getTime() - obj.time > StorageUtil.expireTime) {
            // console.log("信息已过期");
            localStorage.removeItem(key);
            return null;
         } else {
            return JSON.parse(obj.data);
         }
      } catch (e) {
         localStorage.removeItem(key);
         // console.error("json解析异常：" + e);
         return null;
      }
   }


   getNotExpire(key: any): any {
      let data = localStorage.getItem(key);
      if (!data) {
         return null;
      }
      try {
         let obj = JSON.parse(data);
         return JSON.parse(obj.data);
      } catch (e) {
         localStorage.removeItem(key);
         // console.error("json解析异常：" + e);
         return null;
      }
   }

   /**
    * 获取 key 的剩余有效时间
    * @param key
    * @returns {number}
    */
   getExpireTime(key: any): number {
      let data = localStorage.getItem(key);
      if (!data) {
         return null;
      }
      try {
         let obj = JSON.parse(data);
         let expire = new Date().getTime() - obj.time;
         if (expire > StorageUtil.expireTime) {
            localStorage.removeItem(key);
            return null;
         } else {
            return StorageUtil.expireTime - expire;
         }
      } catch (e) {
         localStorage.removeItem(key);
         // console.error("json解析异常：" + e);
         return null;
      }
   }

   /**
    * 判断 key 是否有效
    * @param key
    * @returns {boolean}
    */
   expires(key: any): boolean {
      let data = localStorage.getItem(key);
      if (!data) {
         return false;
      }
      try {
         let obj = JSON.parse(data);
         let expire = new Date().getTime() - obj.time;
         if (expire > StorageUtil.expireTime) {
            localStorage.removeItem(key);
            return false;
         } else {
            return true;
         }
      } catch (e) {
         localStorage.removeItem(key);
         // console.error("json解析异常：" + e);
         return false;
      }
   }

}
