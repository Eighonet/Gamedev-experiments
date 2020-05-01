package com.eighonet.pinball.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.eighonet.pinball.BodyEditorLoader;
import com.eighonet.pinball.TexturePacker;

public class Ball extends TexturePacker {
	
	public Ball(World world) {
		super(world);
	}



	protected static final float WIDTH = (float) 0.46;


	
	public void Create() {
		FileHandle file = Gdx.files.internal("data/border.json");
		BodyEditorLoader loader = new BodyEditorLoader(file);
		
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;
		bd.position.set((float)3.2,1);
		
		
		FixtureDef fd = new FixtureDef();
		
		fd.density = 1;
		fd.friction = 0.5f;
		fd.restitution = 0.3f;


		model = world.createBody(bd);
		model.setUserData("BALL"); 
		loader.attachFixture(model, "ball", fd, (float) WIDTH);
		modelOrigin = loader.getOrigin("ball", WIDTH).cpy();
		
		this.sprite = Pack(texture, "data/gfx/ball-marked.png", WIDTH);
	}
	
}
