package com.endDoc.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.endDoc.dao.IStatisticsDao;
import com.endDoc.po.UserPo;
import com.endDoc.vo.StatisticsVo;

public class StatisticsDao extends HibernateDaoSupport implements
		IStatisticsDao {
	public List<StatisticsVo> statistics(String username, String time) {
		List<StatisticsVo> list = new ArrayList<StatisticsVo>();
		if ((username != null) && (username.length() != 0)) {
			StatisticsVo vo = new StatisticsVo();
			if (exist(username) == null)
				return new ArrayList<StatisticsVo>();
			vo.setUsername(username);
			System.out.print(username);
			System.out.print(time);
			if ((time == null) || (time.length() == 0) || ("all".equals(time))) {
				vo.setCountDoc(countDocument(username));
				vo.setCountAttachment(countAttachment(username));
				vo.setCountBrief(countBriefComment(username));
				vo.setCountDetail(countDetailComment(username));
				list.add(vo);
			} else {
				vo.setCountDoc(countDocument2(username, time));
				vo.setCountAttachment(countAttachment2(username, time));
				vo.setCountBrief(countBriefComment2(username, time));
				vo.setCountDetail(countDetailComment2(username, time));
				list.add(vo);
			}
		} else if ((username == null) || (username.length() == 0)) {
			List<UserPo> userList = findAll();
			if ((time == null) || (time.length() == 0) || ("all".equals(time))) {
				for (int i = 0; i < userList.size(); i++) {
					StatisticsVo vo = new StatisticsVo();
					vo.setUsername(userList.get(i).getUsername());
					vo.setCountDoc(countDocument(userList.get(i).getUsername()));
					vo.setCountAttachment(countAttachment(userList.get(i)
							.getUsername()));
					vo.setCountBrief(countBriefComment(userList.get(i)
							.getUsername()));
					vo.setCountDetail(countDetailComment(userList.get(i)
							.getUsername()));
					list.add(vo);
				}
			} else {
				for (int i = 0; i < userList.size(); i++) {
					StatisticsVo vo = new StatisticsVo();
					vo.setUsername(userList.get(i).getUsername());
					vo.setCountDoc(countDocument2(
							userList.get(i).getUsername(), time));
					vo.setCountAttachment(countAttachment2(userList.get(i)
							.getUsername(), time));
					vo.setCountBrief(countBriefComment2(userList.get(i)
							.getUsername(), time));
					vo.setCountDetail(countDetailComment2(userList.get(i)
							.getUsername(), time));
					list.add(vo);
				}
			}
		}
		return list;
	}

	public int countDocument(String username) {
		String hql = "select count(*) from DocumentPo where username='"
				+ username + "'";
		Long count = (Long) getHibernateTemplate().find(hql).listIterator()
				.next();
		return count.intValue();
	}

	public int countAttachment(String username) {
		String hql = "select count(*) from AttachmentPo where username='"
				+ username + "'";
		Long count = (Long) getHibernateTemplate().find(hql).listIterator()
				.next();
		return count.intValue();
	}

	public int countBriefComment(String username) {
		String hql = "select count(*) from BriefCommentPo where isDraft=0 and username='"
				+ username + "'";
		Long count = (Long) getHibernateTemplate().find(hql).listIterator()
				.next();
		return count.intValue();
	}

	public int countDetailComment(String username) {
		String hql = "select count(distinct did) from DetailCommentPo where isDraft=0 and username='"
				+ username + "'";
		Long count = (Long) getHibernateTemplate().find(hql).listIterator()
				.next();
		return count.intValue();
	}

	public int countDocument2(String username, String time) {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		if ("week".equals(time)) {
			calendar.add(Calendar.WEEK_OF_MONTH, -1);
		}
		if ("month".equals(time)) {
			calendar.add(Calendar.MONTH, -1);
		}
		if ("year".equals(time)) {
			calendar.add(Calendar.YEAR, -1);
		}
		Date startdate = calendar.getTime();
		System.out.print(date);
		System.out.print(startdate);
		String hql = "select count(*) from DocumentPo where username = :username and create_time >=:startdate and create_time <= :date";
		String[] params = { "username", "startdate", "date" };
		Object[] args = { username, startdate, date };
		Long count = (Long) getHibernateTemplate()
				.findByNamedParam(hql, params, args).listIterator().next();
		return count.intValue();
	}

	public int countAttachment2(String username, String time) {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		if ("week".equals(time)) {
			calendar.add(Calendar.WEEK_OF_MONTH, -1);
		}
		if ("month".equals(time)) {
			calendar.add(Calendar.MONTH, -1);
		}
		if ("year".equals(time)) {
			calendar.add(Calendar.YEAR, -1);
		}
		Date startdate = calendar.getTime();
		System.out.print(date);
		System.out.print(startdate);
		String hql = "select count(*) from AttachmentPo where username = :username and create_time >=:startdate and create_time <= :date";
		String[] params = { "username", "startdate", "date" };
		Object[] args = { username, startdate, date };
		Long count = (Long) getHibernateTemplate()
				.findByNamedParam(hql, params, args).listIterator().next();
		return count.intValue();
	}

	public int countBriefComment2(String username, String time) {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		if ("week".equals(time)) {
			calendar.add(Calendar.WEEK_OF_MONTH, -1);
		}
		if ("month".equals(time)) {
			calendar.add(Calendar.MONTH, -1);
		}
		if ("year".equals(time)) {
			calendar.add(Calendar.YEAR, -1);
		}
		Date startdate = calendar.getTime();
		System.out.print(date);
		System.out.print(startdate);
		String hql = "select count(*) from BriefCommentPo where isDraft=0 and username = :username and create_time >=:startdate and create_time <= :date";
		String[] params = { "username", "startdate", "date" };
		Object[] args = { username, startdate, date };
		Long count = (Long) getHibernateTemplate()
				.findByNamedParam(hql, params, args).listIterator().next();
		return count.intValue();
	}

	public int countDetailComment2(String username, String time) {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		if ("week".equals(time)) {
			calendar.add(Calendar.WEEK_OF_MONTH, -1);
		}
		if ("month".equals(time)) {
			calendar.add(Calendar.MONTH, -1);
		}
		if ("year".equals(time)) {
			calendar.add(Calendar.YEAR, -1);
		}
		Date startdate = calendar.getTime();
		System.out.print(date);
		System.out.print(startdate);
		String hql = "select count(distinct did) from DetailCommentPo where isDraft=0 and username = :username and create_time >=:startdate and create_time <= :date";
		String[] params = { "username", "startdate", "date" };
		Object[] args = { username, startdate, date };
		Long count = (Long) getHibernateTemplate()
				.findByNamedParam(hql, params, args).listIterator().next();
		return count.intValue();
	}

	public List<UserPo> findAll() {
		String hql = "from UserPo  order by username";
		List<UserPo> ljs = super.getHibernateTemplate().find(hql);
		return ljs;
	}

	public UserPo exist(String username) {
		String hql = "from UserPo where username = ?";
		List<UserPo> list = this.getHibernateTemplate().find(hql, username);
		UserPo user = list.size() == 0 ? null : list.get(0);
		return user;
	};
}
