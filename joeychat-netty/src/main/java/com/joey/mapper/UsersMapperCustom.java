package com.joey.mapper;

import java.util.List;

import com.joey.pojo.Users;
import com.joey.pojo.vo.FriendRequestVO;
import com.joey.pojo.vo.MyFriendsVO;
import com.joey.utils.MyMapper;

public interface UsersMapperCustom extends MyMapper<Users> {
	
	public List<FriendRequestVO> queryFriendRequestList(String acceptUserId);
	
	public List<MyFriendsVO> queryMyFriends(String userId);
	
	public void batchUpdateMsgSigned(List<String> msgIdList);
	
}