import request from '@/utils/request';

// 查询【请填写功能名称】列表
export function listLabel(query) {
  return request({
    url: '/admin/queryAllLabel',
    method: 'get',
  });
}

export function getLabelByName(name) {
  return request({
    url: '/admin/label',
    method: 'get',
    params: {
      name: name,
    },
  });
}

// 查询【请填写功能名称】详细
export function getLabel(labelId) {
  return request({
    url: '/admin/label/' + labelId,
    method: 'get',
  });
}

// 新增【请填写功能名称】
export function addLabel(data) {
  return request({
    url: '/admin/label',
    method: 'post',
    data: data,
  });
}

// 修改【请填写功能名称】
export function updateLabel(data) {
  return request({
    url: '/admin/label',
    method: 'put',
    data: data,
  });
}

// 删除【请填写功能名称】
export function delLabel(labelId) {
  return request({
    url: '/admin/label/' + labelId,
    method: 'delete',
  });
}
