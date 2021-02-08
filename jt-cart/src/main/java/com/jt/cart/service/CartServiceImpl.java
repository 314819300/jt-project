package com.jt.cart.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.cart.mapper.CartMapper;
import com.jt.cart.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author wangning
 * @create 2021-02-07 15:42
 */
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartMapper cartMapper;

	@Override
	public List<Cart> findCartListByUserId(Long userId) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId);
		return cartMapper.selectList(new QueryWrapper<Cart>().eq("user_id", userId));
	}

	@Override
	@Transactional
	public void updateCartNum(Cart cart) {
		Cart cartTemp = new Cart();
		cartTemp.setNum(cart.getNum());

		UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("user_id", cart.getUserId())
				.eq("item_id", cart.getItemId());
		cartMapper.update(cartTemp, updateWrapper);
	}

	/**
	 * 新增的业务逻辑 item_id,user_id
	 * 如果根据item_id和user_id查询时
	 * 数据可以有记录
	 * 		则只做数据的更新操作
	 * 如果数据库中没有记录
	 * 		则进行数据的新增操作
	 * @param cart
	 */
	@Override
	@Transactional
	public void saveCart(Cart cart) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", cart.getUserId())
				.eq("item_id", cart.getItemId());
		Cart cartDB = cartMapper.selectOne(queryWrapper);
		if(cartDB == null) {
			cart.setCreated(new Date());
			cart.setCreated(cart.getCreated());
			cartMapper.insert(cart);
		} else {
			int num = cart.getNum() + cartDB.getNum();
			cartDB.setNum(num);
			//只根据主键更新数据，并且全部字段更新
//			cartMapper.updateById(cartDB);//这种执行的效率较低
			UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
			updateWrapper.eq("user_id", cart.getUserId())
					.eq("item_id", cart.getItemId());
			Cart cartTemp = new Cart();
			cartTemp.setNum(num);
			cartMapper.update(cartTemp, updateWrapper);
		}

	}

	@Override
	@Transactional
	public void deleteCart(Long userId, Long itemId) {
		QueryWrapper<Cart> wrapper = new QueryWrapper<>();
		wrapper.eq("user_id", userId)
				.eq("item_id", itemId);
		cartMapper.delete(wrapper);
	}
}
