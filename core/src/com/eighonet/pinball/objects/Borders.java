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

public class Borders extends TexturePacker {

	public Borders(World world) {
		super(world);
		// TODO Auto-generated constructor stub
	}

	private Texture bottleTexture, wallTexture, wallLTexture, wallRTexture, subborderTexture;
	private Sprite bottleSprite, wallSprite, wallLSprite, wallRSprite, subborderSprite;
	private Body bottleModel, wallModel, wallLModel, wallRModel, subborderModel;
	private Vector2 bottleModelOrigin,  wallModelOrigin, wallLModelOrigin, 
	wallRModelOrigin, subborderModelOrigin;
	private static final float BOTTLE_WIDTH = 8, WALL_WIDTH = 2, WALL_LR_WIDTH = (float)1.5;
		
	
	
	@Override
	public void Create() {
			FileHandle file = Gdx.files.internal("data/border.json");
			BodyEditorLoader loader = new BodyEditorLoader(file);

			BodyDef bd = new BodyDef();
			BodyDef bdCurve = new BodyDef();
			BodyDef bdL = new BodyDef();
			BodyDef bdR = new BodyDef();
			
			bd.type = BodyType.StaticBody;
			bdCurve.type = BodyType.StaticBody;
			bdL.type = BodyType.StaticBody;
			bdR.type = BodyType.StaticBody;
			
			bd.position.set((float) -4,(float)-6.5);
			bdCurve.position.set((float) -3.1,(float) 0.15);
			bdL.position.set((float) -3.28,-4);
			bdR.position.set((float) 1,-4);

			FixtureDef fd = new FixtureDef();
			fd.density = 0.1f;
			fd.friction = 0.3f;
			fd.restitution = 0.1f;

			bottleModel = world.createBody(bd);
			wallModel = world.createBody(bdCurve);
			wallLModel = world.createBody(bdL);
			wallRModel = world.createBody(bdR);

			loader.attachFixture(bottleModel, "123", fd, BOTTLE_WIDTH);
			loader.attachFixture(wallModel, "wall", fd, WALL_WIDTH);
			loader.attachFixture(wallRModel, "wallR", fd, WALL_LR_WIDTH);
			loader.attachFixture(wallLModel, "wallL", fd, WALL_LR_WIDTH);
			
			bottleModelOrigin = loader.getOrigin("123", BOTTLE_WIDTH).cpy();
			wallModelOrigin = loader.getOrigin("wall", WALL_WIDTH).cpy();
			wallRModelOrigin = loader.getOrigin("wallR", WALL_LR_WIDTH).cpy();
			wallLModelOrigin = loader.getOrigin("wallL", WALL_LR_WIDTH).cpy();
		
			subborderSprite = Pack(bottleTexture, "data/gfx/subborder.png", WALL_WIDTH);
			bottleSprite = Pack(bottleTexture, "data/gfx/backlayer.png", BOTTLE_WIDTH);
			wallLSprite = Pack(wallLTexture, "data/gfx/wallL.png", WALL_LR_WIDTH);
			wallRSprite = Pack(wallRTexture, "data/gfx/wallR.png", WALL_LR_WIDTH);
	}
	
	public void Assembly() {
		bottleSprite = Connect(bottleSprite, bottleModel, bottleModelOrigin);
		wallLSprite = Connect(wallLSprite, wallLModel, wallLModelOrigin);
		wallRSprite = Connect(wallRSprite, wallRModel, wallRModelOrigin);
		subborderSprite = Connect(subborderSprite, wallModel, wallModelOrigin);
	}
	
	public Sprite[] GetSprite() {
		Sprite[] spriteArray = {this.bottleSprite, this.wallLSprite, this.wallRSprite, this.subborderSprite};
		return spriteArray;
	}
}
