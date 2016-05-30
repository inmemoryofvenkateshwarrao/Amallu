package com.amallu.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.amallu.fragment.CommentsFragment.CommentsAdapter.CommentRowViewHolder;
import com.amallu.ui.R;
import com.amallu.utility.ReqResNodes;

public class CommentsFragment extends Fragment{
	
	private static final String TAG="CommentsFragment";
	private TextView comments_unavail_txt_view;
	private CommentRowViewHolder commentRowViewHolder;
	private LayoutInflater commentInflater;
	private ListView comment_list;
	public static ArrayList<HashMap<String,Object>> commentsArrList;
	private OnItemSelectedListener listener;
	private CommentsAdapter commentListAdapter=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);
	  Log.i(TAG,"onCreate() Entering.");
	  Log.i(TAG,"onCreate() Exiting.");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
	  Log.i(TAG,"onCreateView() Entering.");
	  View commentsView = inflater.inflate(R.layout.comments,container,false);
	  comment_list=(ListView)commentsView.findViewById(R.id.listview_comments);
	  comments_unavail_txt_view=(TextView)commentsView.findViewById(R.id.comments_unavail_txt_view);
	  refreshCommentsList(commentsArrList);	
	  Log.i(TAG,"onCreateView() Exiting.");
	  return commentsView;		
	}
	
	//Internal Interface type to communicate with hosted Activity.
	public interface OnItemSelectedListener{
	   public void onCommentsItemSelected(HashMap<String,Object> commentRowHM);
	}
	
	//Execute when Fragment is attached.
	@Override
	public void onAttach(Context context){
	   super.onAttach(context);
	   Log.i(TAG,"onAttach() Entering.");
	   if(context instanceof OnItemSelectedListener){
	     listener=(OnItemSelectedListener)context;
	   }else{
	     //throw new ClassCastException(context.toString()+ " must implemenet MyListFragment.OnItemSelectedListener");
	   }
	   Log.i(TAG,"onAttach() Exiting.");
	 }

	@Override
	public void onDetach(){
	  super.onDetach();
	  Log.i(TAG,"onDetach() Entering.");
	  listener=null;
	  Log.i(TAG,"onDetach() Exiting.");
	}
	
	//Method to refresh CommentsList ListView.
	public void refreshCommentsList(ArrayList<HashMap<String,Object>> commentsArrHMList){
	  Log.i(TAG,"refreshCommentsList() Entering.");
	  
	  if(commentsArrHMList!=null && !commentsArrHMList.isEmpty()){
		 Log.v(TAG,"Comments List Available");
		 comments_unavail_txt_view.setVisibility(View.GONE);
		 populateCommentsList(commentsArrHMList);
		 CommentsFragment.this.commentListAdapter.notifyDataSetChanged();
	  }else{
		 Log.v(TAG,"Comments List not Available");
		 comments_unavail_txt_view.setVisibility(View.VISIBLE);
	  }
	  
	  Log.i(TAG,"refreshCommentsList() Exiting.");
	}
	
	//Populates ListView Data.
	private void populateCommentsList(ArrayList<HashMap<String,Object>> commentsArrHMList){
	  Log.i(TAG,"populateCommentsList() Entering.");
	  commentListAdapter=new CommentsAdapter(getContext(),commentsArrHMList,R.layout.commentrow,new String[]{},new int[]{});
	  comment_list.setAdapter(commentListAdapter);
	  comment_list.setOnItemClickListener(new OnItemClickListener(){
		 @Override
		 public void onItemClick(AdapterView<?> parent, View view, int position,long id){
			HashMap<String,Object> commentRowHM=(HashMap<String,Object>)comment_list.getItemAtPosition(position);
			if(listener!=null){
			  listener.onCommentsItemSelected(commentRowHM);	
			}else{
			  Log.e(TAG,"Error in attaching CommentsFragment to PlayerScreen : listener=null");
			}
		 }
	   });
	  Log.i(TAG,"populateCommentsList() Exiting.");
	}
	
	/*//Method used to Update the UI of Comments if user navigates to Next or Previous Channel.
	public void updateCommentsFragmentUI(ArrayList<HashMap<String,Object>> commentsArrList){
	  Log.i(TAG,"updateCommentsFragmentUI() Entering.");
	  
	  if(commentsArrList!=null&&commentsArrList.size()>0){
		 Log.v(TAG,"Comments exists");
		 Log.d(TAG,"commentsArrList : "+commentsArrList);
		 CommentsAdapter commentListAdapter=new CommentsAdapter(getContext(),commentsArrList,R.layout.commentrow,new String[]{},new int[]{});
		  comment_list.setAdapter(commentListAdapter);
		  commentListAdapter.notifyDataSetChanged();
		  comment_list.setOnItemClickListener(new OnItemClickListener(){
			 @Override
			 public void onItemClick(AdapterView<?> parent, View view, int position,long id){
				HashMap<String,Object> commentRowHM=(HashMap<String,Object>)comment_list.getItemAtPosition(position);
				if(listener!=null){
				  listener.onCommentsItemSelected(commentRowHM);	
				}else{
				  Log.e(TAG,"Error in attaching CommentsFragment to PlayerScreen : listener=null");
				}
			 }
		   });
	  }else{
		 Log.e(TAG,"Comments does not exists for this Channel");
	  }
	  
	  Log.i(TAG,"updateCommentsFragmentUI() Exiting.");
	}*/
	
	//Populates ListView Data.
	/*private void setCommentsData(){
	  Log.i(TAG,"setData() Entering.");
	  CommentsAdapter commentListAdapter=new CommentsAdapter(getContext(),commentsArrList,R.layout.commentrow,new String[]{},new int[]{});
	  comment_list.setAdapter(commentListAdapter);
	  comment_list.setOnItemClickListener(new OnItemClickListener(){
		 @Override
		 public void onItemClick(AdapterView<?> parent, View view, int position,long id){
			HashMap<String,Object> commentRowHM=(HashMap<String,Object>)comment_list.getItemAtPosition(position);
			if(listener!=null){
			  listener.onCommentsItemSelected(commentRowHM);	
			}else{
			  Log.e(TAG,"Error in attaching CommentsFragment to PlayerScreen : listener=null");
			}
		  }
	   });
	  Log.i(TAG,"setData() Exiting.");
	}*/
	
	//Inner Class to make smooth swiping of list view.
	public class CommentsAdapter extends SimpleAdapter{
		
	   ArrayList<HashMap<String,Object>> commentRowArrList;

	   public CommentsAdapter(Context context, List<HashMap<String,Object>> items,int resource,String[] from,int[] to){
		   super(context, items, resource, from, to);
		   commentRowArrList=(ArrayList<HashMap<String,Object>>)items;
		   commentInflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }
		    
        public View getView(int position,View convertView, ViewGroup parent){
           if(convertView==null){
        	  convertView = commentInflater.inflate(R.layout.commentrow,null);
        	  commentRowViewHolder=new CommentRowViewHolder();
        	  
        	  commentRowViewHolder.comment_uname_txt_view=(TextView)convertView.findViewById(R.id.comment_uname_txt_view);
        	  commentRowViewHolder.time_txt_view=(TextView)convertView.findViewById(R.id.time_txt_view);
        	  commentRowViewHolder.comment_txt_view=(TextView)convertView.findViewById(R.id.comment_txt_view);
        	  commentRowViewHolder.like_count_txt_view=(TextView)convertView.findViewById(R.id.like_count_txt_view);
        	  commentRowViewHolder.dislike_count_txt_view=(TextView)convertView.findViewById(R.id.dislike_count_txt_view);
        	  
        	  commentRowViewHolder.icon_comment_like=(ImageView)convertView.findViewById(R.id.icon_comment_like);
        	  commentRowViewHolder.icon_comment_dislike=(ImageView)convertView.findViewById(R.id.icon_comment_dislike);
        	  convertView.setTag(commentRowViewHolder);
            }else{
            	commentRowViewHolder=(CommentRowViewHolder)convertView.getTag();
            }
            HashMap<String,Object> commentRowHM=(HashMap<String,Object>)commentRowArrList.get(position);
             
            commentRowViewHolder.comment_uname_txt_view.setText(commentRowHM.get(ReqResNodes.USERNAME).toString());
            commentRowViewHolder.time_txt_view.setText(commentRowHM.get(ReqResNodes.DT_CREATED).toString());
            commentRowViewHolder.comment_txt_view.setText(commentRowHM.get(ReqResNodes.COMMENT).toString());
            
            //commentRowViewHolder.like_count_txt_view.setText(commentRowHM.get(ReqResNodes.LIKECOUNT).toString());
            //commentRowViewHolder.dislike_count_txt_view.setText(commentRowHM.get(ReqResNodes.DISLIKECOUNT).toString());
            
            commentRowViewHolder.like_count_txt_view.setText("10");
            commentRowViewHolder.dislike_count_txt_view.setText("6");
            
            /*commentRowViewHolder.icon_comment_like.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					
				}
			});
            commentRowViewHolder.icon_comment_dislike.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					
				}
			});
            commentRowViewHolder.icon_comment_reply.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					
				}
			});
            commentRowViewHolder.icon_comment_edit.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					
				}
			});
            commentRowViewHolder.icon_comment_delete.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					
				}
			});*/
            
            return convertView;
        }
	    
        //Inner Class to hold views of list item.
        class CommentRowViewHolder{
    	    //ImageView icon_comment_like,icon_comment_dislike,icon_comment_reply,icon_comment_edit,icon_comment_delete;
        	ImageView icon_comment_like,icon_comment_dislike;
    	    TextView comment_uname_txt_view,time_txt_view,comment_txt_view,like_count_txt_view,dislike_count_txt_view;
        }
	        
	}
	
}
