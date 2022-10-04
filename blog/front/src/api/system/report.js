import request from '@/utils/request';

// 查询【请填写功能名称】列表
export function listReport(query) {
  console.log('query', query);
  return request({
    url: '/admin/report/list',
    method: 'get',
    params: query,
  });
}

// 查询【请填写功能名称】详细
export function getReport(reportId) {
  return request({
    url: '/admin/report/' + reportId,
    method: 'get',
  });
}

// 新增【请填写功能名称】
export function addReport(data) {
  return request({
    url: '/admin/report',
    method: 'post',
    data: data,
  });
}

// 修改【请填写功能名称】
export function updateReport(data) {
  return request({
    url: '/admin/report',
    method: 'put',
    data: data,
  });
}

// 删除【请填写功能名称】
export function delReport(reportId) {
  return request({
    url: '/admin/report/' + reportId,
    method: 'delete',
  });
}
