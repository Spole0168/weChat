package com.zhenjinzi.yzy.service.impl.security;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.User;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;
import com.zhenjinzi.yzy.model.YzyAdmin;
import com.zhenjinzi.yzy.model.ZunmiPermission;
import com.zhenjinzi.yzy.model.ZunmiRole;


/**
 * 实现SpringSecurity的SystemUserSecurity接口,实现获取用户信息的回调函数.
 * 
 */
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private SecurityEntityManager securityEntityManager;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException,DataAccessException {
		YzyAdmin admin = securityEntityManager.findAdminByName(username);
		if (admin == null)
			throw new UsernameNotFoundException("用户" + username + " 不存在");
		
		
		GrantedAuthority[] grantedAuths = obtainGrantedAuthorities(admin);
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		User userdetail = new User(admin.getUserName(),admin.getPassword(), enabled,accountNonExpired, credentialsNonExpired, accountNonLocked,grantedAuths);
		return userdetail;
	}
	
	/**
	 * 获得用户所有角色的权限集合.
	 * 2016-2-27暂时设置两个默认的角色  ROLE_ABC  和ROLE_ABCD
	 */
	private GrantedAuthority[] obtainGrantedAuthorities(YzyAdmin admin) {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
//		for (ZunmiRole role : admin.getZunmiRoles()) {
//			for (ZunmiPermission permission : role.getPermissions()) {
		authSet.add(new GrantedAuthorityImpl("ROLE_ABC"));
		authSet.add(new GrantedAuthorityImpl("ROLE_ABCD"));
//			}
//		}
		return authSet.toArray(new GrantedAuthority[authSet.size()]);
//		return null;
	}
}
