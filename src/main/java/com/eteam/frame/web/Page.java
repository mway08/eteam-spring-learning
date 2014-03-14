package com.eteam.frame.web;

import java.util.ArrayList;
import java.util.List;

public abstract class Page<T> {

	/** first page */
	private int firstPage;

	/** last page */
	private int lastPage;

	/** next page */
	private int nextPage;

	/** prev page */
	private int prevPage;

	/** current page */
	private int currentPage;

	/** total page */
	private int totalPage;

	/** total count */
	private int rowCount;

	/** page size */
	private int pageSize;

	/** exists next page */
	private boolean hasNext;

	/** exists prev page */
	private boolean hasPrev;

	/** exists first page */
	private boolean hasFirst;

	/** exists last page */
	private boolean hasLast;

	/** rows */
	private List<T> rows;

	/** Constructor default */
	// public Page() {
	// }

	/** call this constructor method get instance */
	public Page(int currentPage, int pageSize) {
		this.rowCount = this.doCount();
		if (this.rowCount > 0) {
			this.currentPage = currentPage;
			this.pageSize = pageSize;
			this.totalPage = this.rowCount % pageSize == 0 ? this.rowCount
					/ pageSize : this.rowCount / pageSize + 1;
			if (this.currentPage < 1) {
				this.currentPage = 1;
			}
			if (this.currentPage > this.totalPage) {
				this.currentPage = this.totalPage;
			}
			if (this.totalPage > 0) {
				this.hasFirst = true;
				this.firstPage = 1;
			}
			if (this.currentPage > 1) {
				this.hasPrev = true;
				this.prevPage = this.currentPage - 1;
			}
			if (this.totalPage > 0 && this.currentPage < this.totalPage) {
				this.hasNext = true;
				this.nextPage = this.currentPage + 1;
			}
			if (this.totalPage > 0) {
				this.hasLast = true;
				this.lastPage = this.totalPage;
			}

			// query current page
			int offset = (this.currentPage - 1) * pageSize;
			this.rows = this.doQuery(offset, pageSize);
		} else {
			this.firstPage = 1;
			this.lastPage = 1;
			this.nextPage = 1;
			this.prevPage = 1;
			this.currentPage = 1;
			this.totalPage = 1;
			this.hasNext = false;
			this.hasPrev = false;
			this.hasFirst = false;
			this.hasLast = false;
			this.rows = new ArrayList<T>();
		}
	}

	protected abstract int doCount();

	protected abstract List<T> doQuery(int offset, int limit);

	/** property firstPage getter method */
	public int getFirstPage() {
		return firstPage;
	}

	/** property firstPage setter method */
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	/** property lastPage getter method */
	public int getLastPage() {
		return lastPage;
	}

	/** property lastPage setter method */
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	/** property nextPage getter method */
	public int getNextPage() {
		return nextPage;
	}

	/** property nextPage setter method */
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	/** property prevPage getter method */
	public int getPrevPage() {
		return prevPage;
	}

	/** property prevPage setter method */
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	/** property currentPage getter method */
	public int getCurrentPage() {
		return currentPage;
	}

	/** property currentPage setter method */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/** property totalPage getter method */
	public int getTotalPage() {
		return totalPage;
	}

	/** property totalPage setter method */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/** property rowCount getter method */
	public int getRowCount() {
		return rowCount;
	}

	/** property rowCount setter method */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	/** property pageSize getter method */
	public int getPageSize() {
		return pageSize;
	}

	/** property pageSize setter method */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/** property hasNext getter method */
	public boolean isHasNext() {
		return hasNext;
	}

	/** property hasNext setter method */
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	/** property hasPrev getter method */
	public boolean isHasPrev() {
		return hasPrev;
	}

	/** property hasPrev setter method */
	public void setHasPrev(boolean hasPrev) {
		this.hasPrev = hasPrev;
	}

	/** property hasFirst getter method */
	public boolean isHasFirst() {
		return hasFirst;
	}

	/** property hasFirst setter method */
	public void setHasFirst(boolean hasFirst) {
		this.hasFirst = hasFirst;
	}

	/** property hasLast getter method */
	public boolean isHasLast() {
		return hasLast;
	}

	/** property hasLast setter method */
	public void setHasLast(boolean hasLast) {
		this.hasLast = hasLast;
	}

	public List<T> getRows() {
		return this.rows;
	}
}