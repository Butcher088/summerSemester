var OSS = require('ali-oss');
export function client() {
    var client = new OSS({
        region: 'oss-cn-qingdao',
        accessKeyId: 'LTAI5t8fQTxddt611VsMVeAu',
        accessKeySecret: 'jg1qxPbpwl672xIosfXA6E9B4bCCnL',
        bucket: 'our-blog'
    })  //后端提供数据
    return client
}
