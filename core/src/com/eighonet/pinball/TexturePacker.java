package com.eighonet.pinball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class TexturePacker {
	
	protected World world;
	
	protected Texture texture;
	
	protected Sprite sprite;
	
	protected Sprite[] spriteArray;
	
	protected Body model;
	
	protected Vector2 modelOrigin;
	
	protected static final float WIDTH = 0;
	
	public TexturePacker (World world) {
		this.world = world;
	}
	
	protected Sprite Pack(Texture texture, String path, float WIDTH) {
		System.out.println(path);
		texture = new Texture(Gdx.files.internal(path));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Sprite sprite = new Sprite(texture);
		sprite.setSize(WIDTH, WIDTH*sprite.getHeight()/sprite.getWidth());
		return sprite;
	}
	
	protected Sprite Connect(Sprite sprite, Body model, Vector2 origin) {
		Vector2 Pos = model.getPosition().sub(origin);
		sprite.setPosition(Pos.x, Pos.y);
		sprite.setOrigin(origin.x, origin.y);
		sprite.setRotation(model.getAngle() * MathUtils.radiansToDegrees);
		return sprite;
	}
	
	public void Create() {
		
	}
	
	public void Assembly() {
		Connect(this.sprite, this.model, this.modelOrigin);
	}
	
	public Sprite[] GetSprite() {
		Sprite[] spriteArray = {this.sprite};
		return spriteArray;
	}
	
	public Body GetModel() {
		return model;
	}
	
	public Vector2 GetOrigin() {
		return modelOrigin;
	}
}
