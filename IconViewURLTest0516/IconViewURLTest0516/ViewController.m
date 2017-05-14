//
//  ViewController.m
//  IconViewURLTest0516
//
//  Created by 李龙 on 2017/5/14.
//  Copyright © 2017年 李龙. All rights reserved.
//

#import "ViewController.h"
#import "UIImageView+WebCache.h"
#import "TwoViewController.h"

@interface ViewController ()
@property (weak, nonatomic) IBOutlet UIImageView *iconView1;
@property (weak, nonatomic) IBOutlet UIImageView *iconView2;
@property (weak, nonatomic) IBOutlet UIImageView *iconView3;
@property (weak, nonatomic) IBOutlet UIImageView *iconView4;
@property (weak, nonatomic) IBOutlet UIImageView *iconView5;
@property (weak, nonatomic) IBOutlet UIImageView *iconView6;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    
    [self.iconView1 sd_setImageWithURL:[NSURL URLWithString:@"http://0.image.kw.okbuycdn.com/nbn/n500_500/static/d1a595267f154730d16c384f92ff45e7.jpg"] placeholderImage:nil];
    [self.iconView2 sd_setImageWithURL:[NSURL URLWithString:@"http://0.image.kw.okbuycdn.com/nbn/n500_500/static/cc81fa68d95cf9523284a2f1bd5d3a69.jpg"] placeholderImage:nil];
    [self.iconView3 sd_setImageWithURL:[NSURL URLWithString:@"http://0.image.lx.okbuycdn.com/nbn/n500_500/static/533808a629092b4f40b3327a50fd1363.jpg"] placeholderImage:nil];
    [self.iconView4 sd_setImageWithURL:[NSURL URLWithString:@"http://0.image.lx.okbuycdn.com/nbn/n500_500/static/2c6412c3582dfcfd05e38f559bd033d0.jpg"] placeholderImage:nil];
    [self.iconView5 sd_setImageWithURL:[NSURL URLWithString:@"http://0.image.lx.okbuycdn.com/nbn/n500_500/static/769cb6834797211bd91b9f5c8d1e2e49.jpg"] placeholderImage:nil];
    [self.iconView6 sd_setImageWithURL:[NSURL URLWithString:@"http://0.image.lx.okbuycdn.com/nbn/n500_500/static/3d64bf5424b46f6e96c9bd6e6e0c5f10.jpg"] placeholderImage:nil];
    
}

- (IBAction)aaaaa:(id)sender {
    
    TwoViewController *twoVC = [[TwoViewController alloc] init];
    [self.navigationController pushViewController:twoVC animated:NO];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
