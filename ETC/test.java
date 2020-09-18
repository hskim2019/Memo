// 
// Decompiled by Procyon v0.5.36
// 

package com.naon.biz.gw.precord.controller;

import com.naon.biz.gw.precord.bean.PersonnelPayDetailBean;
import com.naon.biz.gw.precord.bean.PersonnelWorkingBean;
import com.naon.util.DateUtil;
import java.util.GregorianCalendar;
import com.naon.biz.gw.precord.bean.PersonnelMasterBean;
import java.util.ArrayList;
import com.naon.biz.gw.precord.bean.PersonnelLicenseBean;
import java.util.List;
import com.naon.framework.session.GwSession;
import com.naon.biz.gw.precord.bean.PersonnelRecordBean;
import com.naon.framework.web.util.SessionUtil;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.naon.biz.gw.precord.service.PersonnelRecordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.naon.framework.web.controller.BaseMultiActionController;

@Controller
@RequestMapping({ "/{sitemesh}/precord" })
public class PersonnelRecordController extends BaseMultiActionController
{
    @Autowired
    private PersonnelRecordService pRecordService;
    
    @RequestMapping({ "/precordBase" })
    public String precordBase(final HttpServletRequest request, final HttpServletResponse response) {
        final GwSession session = SessionUtil.getUserInfo(request);
        PersonnelRecordBean pBean = new PersonnelRecordBean();
        final PersonnelRecordBean dBean = new PersonnelRecordBean();
        if (session.getUserId().equals("systemadmin")) {
            pBean.setUserName(request.getParameter("sName"));
            if (request.getParameter("sName") == null) {
                pBean.setUserName("");
            }
        }
        pBean.setEmpId(session.getEmpId());
        pBean = this.pRecordService.getBaseInfo(pBean);
        if (pBean != null) {
            pBean.setEmpId(session.getEmpId());
        }
        request.setAttribute("pBean", (Object)pBean);
        request.setAttribute("suser", (Object)session.getEmpId());
        return "/jsp/biz/gw/precord/precordBase";
    }
    
    @RequestMapping({ "/precordDetail" })
    public String getDetailInfo(final HttpServletRequest request, final HttpServletResponse response) {
        final GwSession session = SessionUtil.getUserInfo(request);
        PersonnelRecordBean pBean = new PersonnelRecordBean();
        if (session.getUserId().equals("systemadmin")) {
            pBean.setUserName(request.getParameter("sName"));
            if (request.getParameter("sName") == null) {
                pBean.setUserName("");
            }
        }
        pBean.setEmpId(session.getEmpId());
        pBean = this.pRecordService.getDetailInfo(pBean);
        if (pBean != null) {
            pBean.setEmpId(session.getEmpId());
        }
        request.setAttribute("pBean", (Object)pBean);
        request.setAttribute("suser", (Object)session.getEmpId());
        return "/jsp/biz/gw/precord/precordDetail";
    }
    
    @RequestMapping({ "/precordSchool" })
    public String precordDetail(final HttpServletRequest request, final HttpServletResponse response) {
        final GwSession session = SessionUtil.getUserInfo(request);
        final PersonnelRecordBean pBean = new PersonnelRecordBean();
        if (session.getUserId().equals("systemadmin")) {
            pBean.setUserName(request.getParameter("sName"));
            if (request.getParameter("sName") == null) {
                pBean.setUserName("");
            }
        }
        pBean.setEmpId(session.getEmpId());
        final List<PersonnelRecordBean> list = (List<PersonnelRecordBean>)this.pRecordService.getSchoolList(pBean);
        request.setAttribute("plist", (Object)list);
        request.setAttribute("pBean", (Object)pBean);
        return "/jsp/biz/gw/precord/precordSchool";
    }
    
    @RequestMapping({ "/precordGrade" })
    public String precordGrade(final HttpServletRequest request, final HttpServletResponse response) {
        final GwSession session = SessionUtil.getUserInfo(request);
        final PersonnelRecordBean pBean = new PersonnelRecordBean();
        if (session.getUserId().equals("systemadmin")) {
            pBean.setUserName(request.getParameter("sName"));
            if (request.getParameter("sName") == null) {
                pBean.setUserName("");
            }
        }
        pBean.setEmpId(session.getEmpId());
        final List<PersonnelRecordBean> list = (List<PersonnelRecordBean>)this.pRecordService.getGradeList(pBean);
        request.setAttribute("plist", (Object)list);
        request.setAttribute("pBean", (Object)pBean);
        return "/jsp/biz/gw/precord/precordGrade";
    }
    
    @RequestMapping({ "/precordLicense" })
    public String precordLicense(final HttpServletRequest request, final HttpServletResponse response) {
        final GwSession session = SessionUtil.getUserInfo(request);
        final PersonnelLicenseBean pBean = new PersonnelLicenseBean();
        if (session.getUserId().equals("systemadmin")) {
            pBean.setUserName(request.getParameter("sName"));
            if (request.getParameter("sName") == null) {
                pBean.setUserName("");
            }
        }
        pBean.setEmpId(session.getEmpId());
        final List<PersonnelLicenseBean> list = (List<PersonnelLicenseBean>)this.pRecordService.getLicenseList(pBean);
        request.setAttribute("plist", (Object)list);
        request.setAttribute("pBean", (Object)pBean);
        return "/jsp/biz/gw/precord/precordLicense";
    }
    
    @RequestMapping({ "/precordPenalty" })
    public String precordPenalty(final HttpServletRequest request, final HttpServletResponse response) {
        final GwSession session = SessionUtil.getUserInfo(request);
        final PersonnelLicenseBean pBean = new PersonnelLicenseBean();
        if (session.getUserId().equals("systemadmin")) {
            pBean.setUserName(request.getParameter("sName"));
            if (request.getParameter("sName") == null) {
                pBean.setUserName("");
            }
        }
        pBean.setEmpId(session.getEmpId());
        final List<PersonnelLicenseBean> list = (List<PersonnelLicenseBean>)this.pRecordService.getPenaltyList(pBean);
        request.setAttribute("plist", (Object)list);
        request.setAttribute("pBean", (Object)pBean);
        return "/jsp/biz/gw/precord/precordPenalty";
    }
    
    @RequestMapping({ "/precordEdu" })
    public String precordEdu(final HttpServletRequest request, final HttpServletResponse response) {
        final GwSession session = SessionUtil.getUserInfo(request);
        final PersonnelLicenseBean pBean = new PersonnelLicenseBean();
        if (session.getUserId().equals("systemadmin")) {
            pBean.setUserName(request.getParameter("sName"));
            if (request.getParameter("sName") == null) {
                pBean.setUserName("");
            }
        }
        pBean.setEmpId(session.getEmpId());
        final List<PersonnelLicenseBean> list = (List<PersonnelLicenseBean>)this.pRecordService.getEduList(pBean);
        request.setAttribute("plist", (Object)list);
        request.setAttribute("pBean", (Object)pBean);
        return "/jsp/biz/gw/precord/precordEdu";
    }
    
    @RequestMapping({ "/precordResult" })
    public String precordResult(final HttpServletRequest request, final HttpServletResponse response) {
        final GwSession session = SessionUtil.getUserInfo(request);
        final PersonnelLicenseBean pBean = new PersonnelLicenseBean();
        if (session.getUserId().equals("systemadmin")) {
            pBean.setUserName(request.getParameter("sName"));
            if (request.getParameter("sName") == null) {
                pBean.setUserName("");
            }
        }
        pBean.setEmpId(session.getEmpId());
        final List<PersonnelLicenseBean> list = (List<PersonnelLicenseBean>)this.pRecordService.getResList(pBean);
        request.setAttribute("plist", (Object)list);
        request.setAttribute("pBean", (Object)pBean);
        return "/jsp/biz/gw/precord/precordResult";
    }
    
    @RequestMapping({ "/precordAppo" })
    public String precordAppo(final HttpServletRequest request, final HttpServletResponse response) {
        final GwSession session = SessionUtil.getUserInfo(request);
        PersonnelLicenseBean pBean = new PersonnelLicenseBean();
        pBean.setDeptId(session.getDeptId());
        pBean.setEmpId(session.getEmpId());
        pBean = this.pRecordService.getChrgWork(pBean);
        if (session.getUserId().equals("systemadmin") || pBean != null) {
            if (session.getUserId().equals("systemadmin") || pBean.getJobType().substring(0, 1).equals("1")) {
                pBean = new PersonnelLicenseBean();
                pBean.setUserName(request.getParameter("sName"));
                if (request.getParameter("sName") == null) {
                    pBean.setUserName(session.getUserName());
                }
                request.setAttribute("mgPre", (Object)"true");
            }
        }
        else {
            pBean = new PersonnelLicenseBean();
        }
        pBean.setDeptId(session.getDeptId());
        pBean.setEmpId(session.getEmpId());
        final List<PersonnelLicenseBean> list = (List<PersonnelLicenseBean>)this.pRecordService.getAppoList(pBean);
        request.setAttribute("plist", (Object)list);
        request.setAttribute("pBean", (Object)pBean);
        return "/jsp/biz/gw/precord/precordAppo";
    }
    
    @RequestMapping({ "/precordWorkingDaily" })
    public String precordWorkingDaily(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final GwSession session = SessionUtil.getUserInfo(request);
        final List<PersonnelMasterBean> masterList = new ArrayList<PersonnelMasterBean>();
        PersonnelWorkingBean pBean = null;
        PersonnelWorkingBean wBean = null;
        PersonnelWorkingBean tmpBean = null;
        String wFDate = "";
        String wTDate = "";
        String strToday = "";
        String strDate = "";
        String strYear = "";
        String strMonth = "";
        String strDay = "";
        String tmpYear = "";
        String tmpMonth = "";
        GregorianCalendar today = new GregorianCalendar();
        strDay = DateUtil.getDate(today.getTime(), "dd");
        if (Integer.parseInt(strDay) < 15) {
            today.add(2, -2);
        }
        else {
            today.add(2, -1);
        }
        strToday = DateUtil.getDate(today.getTime(), "yyyyMM");
        if (request.getParameter("workingDate") == null) {
            wFDate = strToday;
            wTDate = strToday;
        }
        else if (request.getParameter("searchMode").equals("multi")) {
            wFDate = request.getParameter("workingFDate");
            wTDate = request.getParameter("workingDate");
        }
        else {
            wFDate = request.getParameter("workingDate");
            wTDate = request.getParameter("workingDate");
        }
        if (Integer.parseInt(wTDate) > Integer.parseInt(strToday)) {
            wTDate = strToday;
        }
        strYear = wFDate.substring(0, 4);
        strMonth = wFDate.substring(4, 6);
        tmpYear = wTDate.substring(0, 4);
        tmpMonth = wTDate.substring(4, 6);
        final int difMonthF = Integer.parseInt(strMonth);
        final int difMonthT = Integer.parseInt(tmpMonth);
        final int difYearF = Integer.parseInt(strYear);
        final int difYearT = Integer.parseInt(tmpYear);
        final int difYearCnt = difYearT - difYearF;
        int difMonthCnt = difMonthT - difMonthF;
        difMonthCnt += difYearCnt * 12;
        request.setAttribute("wFDate", (Object)wFDate);
        request.setAttribute("wTDate", (Object)wTDate);
        request.setAttribute("empId", (Object)session.getEmpId());
        request.setAttribute("sName", (Object)request.getParameter("sName"));
        for (int idx = 0; idx <= difMonthCnt; ++idx) {
            final PersonnelMasterBean mBean = new PersonnelMasterBean();
            pBean = new PersonnelWorkingBean();
            wBean = new PersonnelWorkingBean();
            if (idx == 0) {
                mBean.setFirstMonth("true");
            }
            if (idx == difMonthCnt && Integer.parseInt(strToday) > Integer.parseInt(wTDate)) {
                mBean.setLastMonth("true");
            }
            strDate = wFDate;
            strYear = strDate.substring(0, 4);
            strMonth = strDate.substring(4, 6);
            today = new GregorianCalendar(Integer.parseInt(strYear), Integer.parseInt(strMonth) - 1, 1);
            today.add(2, idx);
            pBean.setYymm(DateUtil.getDate(today.getTime(), "yyyyMM"));
            pBean.setCldr_mm(DateUtil.getDate(today.getTime(), "MM"));
            pBean.setEndDay(today.getActualMaximum(5));
            pBean.setStartWeek(DateUtil.getDayOfWeek(today.getTime()));
            today.add(2, 1);
            pBean.setNextMonth(DateUtil.getDate(today.getTime(), "yyyyMM"));
            today.add(2, -2);
            pBean.setPrevMonth(DateUtil.getDate(today.getTime(), "yyyyMM"));
            List<PersonnelWorkingBean> list = (List<PersonnelWorkingBean>)this.pRecordService.getMonthCalendar(pBean);
            final List<PersonnelWorkingBean> resList = new ArrayList<PersonnelWorkingBean>();
            mBean.setHolyList((List)list);
            if (session.getUserId().equals("systemadmin")) {
                pBean.setUserName(request.getParameter("sName"));
                if (request.getParameter("sName") == null) {
                    pBean.setUserName("");
                }
            }
            pBean.setEmpId(session.getEmpId());
            list = (List<PersonnelWorkingBean>)this.pRecordService.getWorkingDate(pBean);
            if (list != null && list.size() != 0) {
                strDay = list.get(0).getYmd();
                final int intDay = Integer.parseInt(strDay.substring(6, 8));
                for (int i = 0; i <= 31; ++i) {
                    if (i == intDay - 1) {
                        for (int s = 0; s < list.size(); ++s) {
                            resList.add(list.get(s));
                        }
                        break;
                    }
                    tmpBean = new PersonnelWorkingBean();
                    resList.add(tmpBean);
                }
            }
            mBean.setResList((List)resList);
            wBean = this.pRecordService.getWorkingDaily(pBean);
            mBean.setwBean(wBean);
            mBean.setpBean(pBean);
            masterList.add(mBean);
        }
        request.setAttribute("masterList", (Object)masterList);
        return "/jsp/biz/gw/precord/precordWorkingDaily";
    }
    
    @RequestMapping({ "/precordPayDetail" })
    public String precordPayDetail(final HttpServletRequest request, final HttpServletResponse response) {
        final GwSession session = SessionUtil.getUserInfo(request);
        final List<PersonnelPayDetailBean> masterList = new ArrayList<PersonnelPayDetailBean>();
        PersonnelPayDetailBean pBean = null;
        String wFDate = "";
        String wTDate = "";
        String strToday = "";
        String strDate = "";
        GregorianCalendar today = new GregorianCalendar();
        final String strDay = DateUtil.getDate(today.getTime(), "dd");
        if (Integer.parseInt(strDay) < 15) {
            today.add(2, -1);
        }
        strToday = DateUtil.getDate(today.getTime(), "yyyyMM");
        if (request.getParameter("workingDate") == null) {
            wFDate = strToday;
            wTDate = strToday;
        }
        else if (request.getParameter("searchMode").equals("multi")) {
            wFDate = request.getParameter("workingFDate");
            wTDate = request.getParameter("workingDate");
        }
        else {
            wFDate = request.getParameter("workingDate");
            wTDate = request.getParameter("workingDate");
        }
        if (Integer.parseInt(wTDate) > Integer.parseInt(strToday)) {
            wTDate = strToday;
        }
        String strYear = wFDate.substring(0, 4);
        String strMonth = wFDate.substring(4, 6);
        final String tmpYear = wTDate.substring(0, 4);
        final String tmpMonth = wTDate.substring(4, 6);
        final int difMonthF = Integer.parseInt(strMonth);
        final int difMonthT = Integer.parseInt(tmpMonth);
        final int difYearF = Integer.parseInt(strYear);
        final int difYearT = Integer.parseInt(tmpYear);
        final int difYearCnt = difYearT - difYearF;
        int difMonthCnt = difMonthT - difMonthF;
        difMonthCnt += difYearCnt * 12;
        request.setAttribute("wFDate", (Object)wFDate);
        request.setAttribute("wTDate", (Object)wTDate);
        request.setAttribute("empId", (Object)session.getEmpId());
        request.setAttribute("sName", (Object)request.getParameter("sName"));
        for (int i = 0; i <= difMonthCnt; ++i) {
            pBean = new PersonnelPayDetailBean();
            if (session.getUserId().equals("systemadmin")) {
                pBean.setUserName(request.getParameter("sName"));
                if (request.getParameter("sName") == null) {
                    pBean.setUserName("");
                }
            }
            pBean.setEmpId(session.getEmpId());
            strDate = wFDate;
            strYear = strDate.substring(0, 4);
            strMonth = strDate.substring(4, 6);
            today = new GregorianCalendar(Integer.parseInt(strYear), Integer.parseInt(strMonth) - 1, 1);
            today.add(2, i);
            pBean.setYymm(DateUtil.getDate(today.getTime(), "yyyyMM"));
            pBean = this.pRecordService.getPayDetail(pBean);
            if (pBean == null) {
                pBean = new PersonnelPayDetailBean();
                pBean.setYmd(DateUtil.getDate(today.getTime(), "yyyyMM") + "00");
            }
            today.add(2, 1);
            pBean.setNextMonth(DateUtil.getDate(today.getTime(), "yyyyMM"));
            today.add(2, -2);
            pBean.setPrevMonth(DateUtil.getDate(today.getTime(), "yyyyMM"));
            if (i == 0) {
                pBean.setFirstMonth("true");
            }
            if (i == difMonthCnt && Integer.parseInt(strToday) > Integer.parseInt(wTDate)) {
                pBean.setLastMonth("true");
            }
            masterList.add(pBean);
        }
        request.setAttribute("masterList", (Object)masterList);
        return "/jsp/biz/gw/precord/precordPayDetail";
    }
}