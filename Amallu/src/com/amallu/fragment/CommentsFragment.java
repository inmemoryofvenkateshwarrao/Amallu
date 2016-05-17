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
import android.view.View.OnClickListener;
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
	private CommentRowViewHolder commentRowViewHolder;
	private LayoutInflater commentInflater;
	private ListView comment_list;
	public static ArrayList<HashMap<String,Object>> commentsArrList;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
		Log.i(TAG,"onCreateView() Entering.");
		View commentsView = inflater.inflate(R.layout.comments,container,false);
		comment_list=(ListView)commentsView.findViewById(R.id.listview_comments);
		setCommentsData();	
		Log.i(TAG,"onCreateView() Exiting.");
		return commentsView;		
	}
	
	//Populates ListView Data.
	private void setCommentsData(){
	  Log.i(TAG,"setData() Entering.");
	  CommentsAdapter commentListAdapter=new CommentsAdapter(getContext(),commentsArrList,R.layout.commentrow, new String[]{},new int[]{});
	  comment_list.setAdapter(commentListAdapter);
	  comment_list.setOnItemClickListener(new OnItemClickListener(){
		 @Override
		 public void onItemClick(AdapterView<?> parent, View view, int position,long id){
			HashMap<String,Object> commentRowHM=(HashMap<String, Object>)comment_list.getItemAtPosition(position);
			//String categoryID=commentRowHM.get(ReqResNodes.CATEGORY_ID).toString();
			//Log.d(TAG,"Category ID : "+categoryID);
			//sendChannelsByCategoryReq(categoryID);  
		 }
	   });
	  Log.i(TAG,"setData() Exiting.");
	}
	
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
        	  /*commentRowViewHolder.icon_comment_reply=(ImageView)convertView.findViewById(R.id.icon_comment_reply);
        	  commentRowViewHolder.icon_comment_edit=(ImageView)convertView.findViewById(R.id.icon_comment_edit);
        	  commentRowViewHolder.icon_comment_delete=(ImageView)convertView.findViewById(R.id.icon_comment_delete);*/
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
            
            commentRowViewHolder.icon_comment_like.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					
				}
			});
            commentRowViewHolder.icon_comment_dislike.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					
				}
			});
            /*commentRowViewHolder.icon_comment_reply.setOnClickListener(new OnClickListener(){
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
