package safebox.yiye.apackage.com.indextest.bean;

import java.util.List;


public class LoginBackBean extends BaseBean {
	private String hasAlias;

	public String getHasAlias() {
		return hasAlias;
	}

	public void setHasAlias(String hasAlias) {
		this.hasAlias = hasAlias;
	}

	private DataEntity data;

	public void setData(DataEntity data) {
		this.data = data;
	}

	public DataEntity getData() {
		return data;
	}

	public static class DataEntity {
		private String currentOperatorComDepartmentId;
		private String currentOperatorComDepartmentName;
		private String currentOperatorComId;
		private String currentOperatorComName;
		private long currentOperatorId;
		private String currentOperatorName;
		private String currentOperatorNum;
		private String sessionId;
		private String accountName;
		private String currentOperatorType;
		private String currentOperatorlinkMain;
		private String currentOperatorlinkId;

		private List<MenuEntity> menu;

		public void setCurrentOperatorComDepartmentId(
				String currentOperatorComDepartmentId) {
			this.currentOperatorComDepartmentId = currentOperatorComDepartmentId;
		}

		public void setCurrentOperatorComDepartmentName(
				String currentOperatorComDepartmentName) {
			this.currentOperatorComDepartmentName = currentOperatorComDepartmentName;
		}

		public void setCurrentOperatorComId(String currentOperatorComId) {
			this.currentOperatorComId = currentOperatorComId;
		}

		public void setCurrentOperatorComName(String currentOperatorComName) {
			this.currentOperatorComName = currentOperatorComName;
		}

		public void setCurrentOperatorId(long currentOperatorId) {
			this.currentOperatorId = currentOperatorId;
		}

		public void setCurrentOperatorName(String currentOperatorName) {
			this.currentOperatorName = currentOperatorName;
		}

		public void setCurrentOperatorNum(String currentOperatorNum) {
			this.currentOperatorNum = currentOperatorNum;
		}

		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}

		public void setMenu(List<MenuEntity> menu) {
			this.menu = menu;
		}

		public String getCurrentOperatorComDepartmentId() {
			return currentOperatorComDepartmentId;
		}

		public String getCurrentOperatorComDepartmentName() {
			return currentOperatorComDepartmentName;
		}

		public String getCurrentOperatorComId() {
			return currentOperatorComId;
		}

		public String getCurrentOperatorComName() {
			return currentOperatorComName;
		}

		public long getCurrentOperatorId() {
			return currentOperatorId;
		}

		public String getCurrentOperatorName() {
			return currentOperatorName;
		}

		public String getCurrentOperatorNum() {
			return currentOperatorNum;
		}

		public String getSessionId() {
			return sessionId;
		}

		public List<MenuEntity> getMenu() {
			return menu;
		}

		public String getAccountName() {
			return accountName;
		}

		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}

		public String getCurrentOperatorType() {
			return currentOperatorType;
		}

		public void setCurrentOperatorType(String currentOperatorType) {
			this.currentOperatorType = currentOperatorType;
		}

		public String getCurrentOperatorlinkMain() {
			return currentOperatorlinkMain;
		}

		public void setCurrentOperatorlinkMain(String currentOperatorlinkMain) {
			this.currentOperatorlinkMain = currentOperatorlinkMain;
		}

		public String getCurrentOperatorlinkId() {
			return currentOperatorlinkId;
		}

		public void setCurrentOperatorlinkId(String currentOperatorlinkId) {
			this.currentOperatorlinkId = currentOperatorlinkId;
		}

		public static class MenuEntity {
			private boolean group;
			private String icon;
			private int menuId;
			private String name;
			private int order;
			private String title;
			private boolean view;

			private List<ChildrenEntity> children;

			public void setGroup(boolean group) {
				this.group = group;
			}

			public void setIcon(String icon) {
				this.icon = icon;
			}

			public void setMenuId(int menuId) {
				this.menuId = menuId;
			}

			public void setName(String name) {
				this.name = name;
			}

			public void setOrder(int order) {
				this.order = order;
			}

			public void setTitle(String title) {
				this.title = title;
			}

			public void setView(boolean view) {
				this.view = view;
			}

			public void setChildren(List<ChildrenEntity> children) {
				this.children = children;
			}

			public boolean isGroup() {
				return group;
			}

			public String getIcon() {
				return icon;
			}

			public int getMenuId() {
				return menuId;
			}

			public String getName() {
				return name;
			}

			public int getOrder() {
				return order;
			}

			public String getTitle() {
				return title;
			}

			public boolean isView() {
				return view;
			}

			public List<ChildrenEntity> getChildren() {
				return children;
			}

			public static class ChildrenEntity {
				private boolean group;
				private String icon;
				private int menuId;
				private String name;
				private int order;
				private String title;
				private boolean view;
				private List<?> children;
				private boolean isShortCut;// 是否是快捷菜单
				private int last;// 是否是最后一个菜单的标识 0代表不是最后一个，1代表是最后一个

				public int getLast() {
					return last;
				}

				public void setLast(int last) {
					this.last = last;
				}

				public boolean isShortCut() {
					return isShortCut;
				}

				public void setShortCut(boolean isShortCut) {
					this.isShortCut = isShortCut;
				}

				public void setGroup(boolean group) {
					this.group = group;
				}

				public void setIcon(String icon) {
					this.icon = icon;
				}

				public void setMenuId(int menuId) {
					this.menuId = menuId;
				}

				public void setName(String name) {
					this.name = name;
				}

				public void setOrder(int order) {
					this.order = order;
				}

				public void setTitle(String title) {
					this.title = title;
				}

				public void setView(boolean view) {
					this.view = view;
				}

				public void setChildren(List<?> children) {
					this.children = children;
				}

				public boolean isGroup() {
					return group;
				}

				public String getIcon() {
					return icon;
				}

				public int getMenuId() {
					return menuId;
				}

				public String getName() {
					return name;
				}

				public int getOrder() {
					return order;
				}

				public String getTitle() {
					return title;
				}

				public boolean isView() {
					return view;
				}

				public List<?> getChildren() {
					return children;
				}
			}
		}
	}
}
